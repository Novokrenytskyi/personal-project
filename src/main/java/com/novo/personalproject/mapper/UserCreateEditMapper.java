package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {
    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
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
        user.setRole(obj.getRole());
        user.setGender(obj.getGender());
        user.setBirthDate(obj.getBirthDate());
    }
}
