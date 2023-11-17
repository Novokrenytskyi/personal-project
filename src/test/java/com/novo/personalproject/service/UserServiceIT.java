package com.novo.personalproject.service;

import com.novo.personalproject.annotation.IT;
import com.novo.personalproject.model.entity.User;
import com.novo.personalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {
    private static final Long USER_ID = 1L;

    private final UserService userService;

    @Test
    void findByIdTest() {
        var actualResult = userService.findUserById(USER_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new User();
        expectedResult.setId(USER_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult.getId(), actual.getId()));

    }

}
