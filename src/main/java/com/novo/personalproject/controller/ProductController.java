package com.novo.personalproject.controller;

import com.novo.personalproject.dto.ProductCreateDto;
import com.novo.personalproject.dto.ProductReadDto;
import com.novo.personalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(@RequestParam(defaultValue = "0") int page, Model model,
                                 @CurrentSecurityContext SecurityContext securityContext) {
        Page<ProductReadDto> dtoPage = productService.findAllProducts(PageRequest.of(page, 5));
        model.addAttribute("dtoPage", dtoPage);
        boolean authenticated = securityContext.getAuthentication().isAuthenticated();
        System.out.println(authenticated);
        return "page/products";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id) {

        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@ModelAttribute @Validated ProductCreateDto productCreateDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        Optional<ProductReadDto> product = productService.createProduct(productCreateDto);
        if (product.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(product);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

}

