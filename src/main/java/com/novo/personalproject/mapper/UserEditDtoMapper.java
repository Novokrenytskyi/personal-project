package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.UserEditDto;
import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Order;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.model.entity.User;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserEditDtoMapper implements Mapper<UserEditDto, User> {

    @Override
    public User map(UserEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserEditDto userEditDto, User user) {
        user.setId(userEditDto.getId());
        user.setEmail(userEditDto.getEmail());
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        user.setGender(userEditDto.getGender());
        user.setBirthDate(userEditDto.getBirthDate());
    }


}
