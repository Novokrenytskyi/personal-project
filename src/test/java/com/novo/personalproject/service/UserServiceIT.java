package com.novo.personalproject.service;

import com.novo.personalproject.annotation.IT;
import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.model.entity.User;
import com.novo.personalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {
    private static final Long USER_ID = 1L;
    private static final Long USER_ID_FOR_DELETE = 11l;

    private static final UserCreateEditDto CREATE_DTO = UserCreateEditDto.builder()
            .email("test@gmail.com")
            .firstName("test")
            .lastName("test")
            .gender(Gender.MALE)
            .role(Role.USER)
            .birthDate(LocalDate.of(1995, 6, 29))
            .build();

    private static final UserCreateEditDto EDIT_DTO = UserCreateEditDto.builder()
            .email("tested@gmail.com")
            .firstName("test")
            .lastName("test")
            .gender(Gender.MALE)
            .role(Role.USER)
            .birthDate(LocalDate.of(1995, 6, 29))
            .build();

    private final UserService userService;

    @Test
    void findByIdTest() {
        var actualResult = userService.findUserById(USER_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new User();
        expectedResult.setId(USER_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult.getId(), actual.getId()));

    }

    @Test
    void findAllTest() {
        var actualResult = userService.getAllUsers().size();

        var expectedResult = 6;

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void createUserTest() {
        var expectedResult  = CREATE_DTO;

        var actualResult = userService.saveUser(expectedResult);

        assertEquals(expectedResult.getEmail(), actualResult.getEmail());

        var actualEntity = userService.findUserById(actualResult.getId());

        assertEquals(actualResult.getId(),actualEntity.get().getId());
    }

    @Test
    void updateUserTest() {
        var expectedResult = "tested@gmail.com";

        var actualResult = userService.updateUser(USER_ID_FOR_DELETE, EDIT_DTO).get().getEmail();

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void deleteUserTest() {
       var actualResult = userService.deleteUser(USER_ID_FOR_DELETE);

       assertTrue(actualResult);
    }

}
