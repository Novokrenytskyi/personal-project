package com.novo.personalproject.service;

import com.novo.personalproject.dao.ProductRepository;
import com.novo.personalproject.dao.ShoppingCartRepository;
import com.novo.personalproject.dao.UserRepository;
import com.novo.personalproject.model.entity.Product;
import com.novo.personalproject.model.entity.ShoppingCart;
import com.novo.personalproject.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShoppingCartService {
    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private final UserRepository userRepository;

    private final ProductRepository productRepository;


    public Map<Product, Integer> getShoppingCartProducts(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if (shoppingCart != null) {
            Hibernate.initialize(shoppingCart.getProducts());
        }
        return shoppingCart.getProducts();
    }

    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Transactional
    public void updateSoppingCart(Integer productId, Integer quantity, String userName) {

        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findByEmail(userName).orElse(null);

        if(product != null && user != null) {
            ShoppingCart shoppingCart = user.getShoppingCart();

            if(shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setUser(user);
            }

            Map<Product, Integer> products = shoppingCart.getProducts();

            if (products.containsKey(product)) {
                Integer actualQuantity = products.get(product) + quantity;
                products.put(product, actualQuantity);
            } else {
                products.put(product, quantity);
            }

            shoppingCart.setProducts(products);
            shoppingCartRepository.saveAndFlush(shoppingCart);

            user.setShoppingCart(shoppingCart);
            userRepository.saveAndFlush(user);
        }
    }
    @Transactional
    public void removeProduct(Integer productId, Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElse(null);
        Product productToRemove = productRepository.findById(productId).orElse(null);

        Map<Product, Integer> products = shoppingCart.getProducts();
        Map<Product, Integer> filteredProducts =  products.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(productToRemove))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        shoppingCart.setProducts(filteredProducts);
        shoppingCartRepository.saveAndFlush(shoppingCart);
    }
}
