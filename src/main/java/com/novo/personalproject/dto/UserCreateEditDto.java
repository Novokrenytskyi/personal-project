package com.novo.personalproject.dto;

import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Order;
import com.novo.personalproject.model.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Value
@Builder
public class UserCreateEditDto {
    @NotBlank(message = "Email is required")
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    String password;

    String firstName;

    String lastName;

    Gender gender;

    LocalDate birthDate;

    Role role;

    @Builder.Default
    List<Order> orders = new ArrayList<>();

}
