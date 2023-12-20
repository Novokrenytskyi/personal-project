package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        user.setRole(Role.USER);
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public void copy(UserCreateEditDto obj, User user) {
        user.setFirstName(obj.getFirstName());
        user.setLastName(obj.getLastName());
        user.setEmail(obj.getEmail());
        user.setGender(obj.getGender());
        user.setBirthDate(obj.getBirthDate());

        Optional.ofNullable(obj.getRole()).ifPresent(user::setRole);

        Optional.ofNullable(obj.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }
}
