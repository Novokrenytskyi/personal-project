package com.novo.personalproject.service;

import com.novo.personalproject.annotation.IT;
import com.novo.personalproject.dto.ProductReadDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.model.entity.ProductType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@IT
@Sql({
        "classpath:sql/data.sql"
})
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProductServiceIT {
    private static final Integer ID = 1;
    private static final ProductReadDto  EXCEPTED_PRODUCT_READ_DTO = ProductReadDto.builder()
            .id(1)
            .description("Description 1")
            .name("Product 1")
            .price(new BigDecimal("10.99"))
            .productType(ProductType.FIRST_TYPE)
            .build();

    @Autowired
    private final ProductService productService;

    @Test
    @Transactional
    void findAllProducts() {
        Long exceptedTotalElements = 30L;
        Long exceptedPageSize = 10L;

        Page<ProductReadDto> result = productService.findAllProducts(PageRequest.of(1, 10));
        assertTrue(result.hasContent());
        assertEquals(exceptedTotalElements, result.getTotalElements());
        assertEquals(exceptedPageSize, result.getSize());
    }

    @Test
    @Transactional
    void findProductById() {
       Optional <ProductReadDto> optionalActualResult = productService.findProductById(ID);
       assertTrue(optionalActualResult.isPresent());

       ProductReadDto actualResult = optionalActualResult.get();
       assertEquals(EXCEPTED_PRODUCT_READ_DTO, actualResult);

    }
}