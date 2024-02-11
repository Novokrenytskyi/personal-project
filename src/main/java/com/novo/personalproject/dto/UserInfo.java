package com.novo.personalproject.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class UserInfo implements Serializable {
    String userRole;
}
