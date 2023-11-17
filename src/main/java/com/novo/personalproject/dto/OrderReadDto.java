package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.DeliveryMethod;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class OrderReadDto {
     Long id;

     LocalDateTime timeOfCreation;

     DeliveryMethod deliveryMethod;

     Long userId;

}
