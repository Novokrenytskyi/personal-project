package com.novo.personalproject.service;

import com.novo.personalproject.annotation.IT;
import com.novo.personalproject.model.entity.Product;
import com.novo.personalproject.model.entity.ProductType;
import com.novo.personalproject.model.entity.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@IT
@Sql({
        "classpath:sql/data.sql"
})
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ShoppingCartServiceIT {
    private static final Long SHOPPING_CART_ID = 1L;

    private static final Product PRODUCT_FOR_ADD = Product.builder()
            .id(4)
            .description("Description 4")
            .name("Product 4")
            .price(new BigDecimal("40.99"))
            .productType(ProductType.FIRST_TYPE)
            .build();

    private static final Product PRODUCT_FOR_REMOVE = Product.builder()
            .id(1)
            .description("Description 1")
            .name("Product 1")
            .price(new BigDecimal("10.99"))
            .productType(ProductType.FIRST_TYPE)
            .build();

    @Autowired
    private final ShoppingCartService shoppingCartService;
    @Autowired
    private final UserService userService;

    @Test
    @Transactional
    void getShoppingCartProducts() {
        Map<Product, Integer> shoppingCartProducts = shoppingCartService.getShoppingCartProducts(SHOPPING_CART_ID);
        assertTrue(!shoppingCartProducts.isEmpty());
    }

    @Test
    @Transactional
    void findById() {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findById(SHOPPING_CART_ID);
        assertTrue(shoppingCart.isPresent());

        assertTrue(shoppingCart.get().getId().equals(SHOPPING_CART_ID));
    }

    @Test
    @Transactional
    void updateSoppingCart() {
        shoppingCartService.updateSoppingCart(PRODUCT_FOR_ADD.getId(), 4, "user1@example.com");
        Long shoppingCartId = userService.findUserById(1L)
                .get()
                .getShoppingCartId();


        Map<Product, Integer> shoppingCartProducts = shoppingCartService.getShoppingCartProducts(shoppingCartId);

        assertEquals(4, shoppingCartProducts.size());

        assertAll(
                () -> assertTrue(shoppingCartProducts.containsKey(PRODUCT_FOR_ADD)),
                () -> assertEquals(4, shoppingCartProducts.get(PRODUCT_FOR_ADD))
        );
    }

    @Test
    @Transactional
    void removeProduct() {
        shoppingCartService.removeProduct(PRODUCT_FOR_REMOVE.getId(), SHOPPING_CART_ID);

        Map<Product, Integer> products = shoppingCartService.findById(SHOPPING_CART_ID)
                .get()
                .getProducts();

        assertTrue(!products.containsValue(PRODUCT_FOR_REMOVE));

    }
}