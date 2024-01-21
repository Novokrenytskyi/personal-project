package com.novo.personalproject.controller;

import com.novo.personalproject.dto.ProductReadDto;
import com.novo.personalproject.service.ProductService;
import com.novo.personalproject.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/face/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/edit")
    public String editShoppingCart(@RequestParam("id") Integer id,
                                   @RequestParam("quantity") Integer quantity,
                                    Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        ProductReadDto productDto = productService.findProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        shoppingCartService.updateSoppingCart(id, quantity, username);

        model.addAttribute("productDto", productDto);
        model.addAttribute("successMessage", "Product successfully added to cart");
        return "product";
    }

    @GetMapping("/{id}")
    public String getShoppingCartProducts(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("products", shoppingCartService.getShoppingCartProducts(id));
        model.addAttribute("id", id);

        return "shopping-cart";
    }

    @PostMapping("/remove")
    public String removeProduct(@RequestParam("productId") Integer productId,
                                @RequestParam("shoppingCartId") Long shoppingCartId,
                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("shoppingCartId", shoppingCartId);
        shoppingCartService.removeProduct(productId, shoppingCartId);

        return "redirect:/face/shopping-cart/" + shoppingCartId;
    }
}
