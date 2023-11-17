package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.OrderReadDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final OrderReadMapper orderReadMapper;
    @Override
    public UserReadDto map(User object) {
        List<OrderReadDto> orders = object.getOrders().stream()
                .map(orderReadMapper::map)
                .toList();

        return UserReadDto.builder()
                .id(object.getId())
                .email(object.getEmail())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .gender(object.getGender())
                .birthDate(object.getBirthDate())
                .role(object.getRole())
                .orders(orders)
                .build();
    }
}
