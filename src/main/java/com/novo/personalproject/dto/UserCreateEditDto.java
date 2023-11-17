package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Order;
import com.novo.personalproject.model.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class UserCreateEditDto {
    String email;

    String firstName;

    String lastName;

    Gender gender;

    LocalDate birthDate;

    Role role;

    @Builder.Default
    List<Order> orders = new ArrayList<>();
}
