package com.novo.personalproject.controller;

import com.novo.personalproject.dto.ProductReadDto;
import com.novo.personalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/face/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(@RequestParam(defaultValue = "0") int page, Model model,
                                 @CurrentSecurityContext SecurityContext securityContext) {
        Page<ProductReadDto> dtoPage = productService.findAllProducts(PageRequest.of(page,5));
        model.addAttribute("dtoPage" ,dtoPage);
        boolean authenticated = securityContext.getAuthentication().isAuthenticated();
        System.out.println(authenticated);
        return "page/products";
    }

    @GetMapping("/{id}")
    public String getProduct(Model model, @PathVariable(name = "id") Integer id) {
        ProductReadDto productDto = productService.findProductById(id).get();
        model.addAttribute("productDto", productDto);
        return "page/product";
    }
}
