package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.ProductType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ProductReadDto {
    Integer id;

    String name;

    String description;

    BigDecimal price;

    ProductType productType;
}
