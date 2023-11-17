package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.DeliveryMethod;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class OrderCreateEditDto {
    LocalDateTime timeOfCreation = LocalDateTime.now();

    DeliveryMethod deliveryMethod;

    Long userId;
}
