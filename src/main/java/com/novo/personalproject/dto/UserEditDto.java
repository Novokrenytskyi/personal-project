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
public class UserEditDto {
    Long id;

    @Email(message = "Invalid email address")
    String email;

    @NotBlank(message = "Field cannot be empty")
    String firstName;

    @NotBlank(message = "Field cannot be empty")
    String lastName;

    @NotNull(message = "Field cannot be empty")
    Gender gender;

    @NotNull(message = "Field cannot be empty")
    LocalDate birthDate;


}
