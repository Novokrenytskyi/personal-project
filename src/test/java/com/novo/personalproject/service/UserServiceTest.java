package com.novo.personalproject.service;

import com.novo.personalproject.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final Long USER_ID = 1L;

    @Mock
    private UserService userService;

    @Test
    void findUserById() {
        User user = new User();
        user.setId(USER_ID);
        doReturn(Optional.of(user))
                .when(userService).findUserById(USER_ID);
        var actualResul = userService.findUserById(USER_ID);

        assertTrue(actualResul.isPresent());

        var expectedUser = new User();
        expectedUser.setId(USER_ID);
        var expectedResult = expectedUser;

        actualResul.ifPresent(actual -> assertEquals(expectedResult, actual));

    }
}