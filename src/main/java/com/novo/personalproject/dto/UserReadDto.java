package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class UserReadDto {

    Long id;

    String email;

    String firstName;

    String lastName;

    Gender gender;

    LocalDate birthDate;

    Role role;

    @Builder.Default
    List<OrderReadDto> orders = new ArrayList<>();
}
