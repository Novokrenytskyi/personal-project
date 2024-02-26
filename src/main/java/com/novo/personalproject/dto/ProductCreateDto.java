package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.ProductType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Value
@Builder
public class ProductCreateDto {
    @NotNull(message = "Field cannot be empty")
    @NotBlank(message = "Field cannot be empty")
    String name;

    @NotNull(message = "Field cannot be empty")
    MultipartFile image;

    @NotNull(message = "Field cannot be empty")
    @NotBlank(message = "Field cannot be empty")
    String description;

    @NotNull(message = "Field cannot be empty")
    @Digits(integer = 10, fraction = 2, message = "Incorrect number of digits after dot")
    ProductType productType;

    @NotNull(message = "Field cannot be empty")
    BigDecimal price;
}
