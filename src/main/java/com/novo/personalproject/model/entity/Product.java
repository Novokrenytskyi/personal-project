package com.novo.personalproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}
