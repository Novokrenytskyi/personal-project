package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.ProductReadDto;
import com.novo.personalproject.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {
    @Override
    public ProductReadDto map(Product object) {
        return ProductReadDto.builder()
                .id(object.getId())
                .name(object.getName())
                .description(object.getDescription())
                .price(object.getPrice())
                .productType(object.getProductType())
                .build();
    }
}
