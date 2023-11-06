package com.novo.personalproject.model.entity;

import lombok.Data;
import lombok.Getter;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;
    private Role role;

}
