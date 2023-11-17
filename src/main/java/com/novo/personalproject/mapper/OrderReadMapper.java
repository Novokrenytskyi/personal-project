package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.OrderReadDto;
import com.novo.personalproject.model.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {
    @Override
    public OrderReadDto map(Order object) {
        return OrderReadDto.builder()
                .id(object.getId())
                .timeOfCreation(object.getTimeOfCreation())
                .deliveryMethod(object.getDeliveryMethod())
                .userId(object.getUser().getId())
                .build();
    }
}
