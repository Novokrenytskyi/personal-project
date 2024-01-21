package com.novo.personalproject.service;

import com.novo.personalproject.annotation.IT;
import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.error.EmailAlreadyExistsException;
import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Sql({
        "classpath:sql/data.sql"
})
@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {

    private final UserService userService;
    private static final Long EXISTED_DTO_ID = 1L;
    private static final UserCreateEditDto EXISTED_USER_CREATE_DTO = UserCreateEditDto.builder()
            .email("user1@example.com")
            .password("password1")
            .firstName("User1")
            .lastName("Last1")
            .gender(Gender.MALE)
            .role(Role.USER)
            .birthDate(LocalDate.of(2000, 1, 1))
            .build();
    private static final UserCreateEditDto NEW_USER_CREATE_DTO = UserCreateEditDto.builder()
            .email("user11@example.com")
            .password("password11")
            .firstName("User11")
            .lastName("Last11")
            .gender(Gender.MALE)
            .role(Role.USER)
            .birthDate(LocalDate.of(2000, 1, 1))
            .build();
    private static final UserCreateEditDto EDITED_USER_CREATE_EDIT_DTO = UserCreateEditDto.builder()
            .email("user1@exampleedited.com")
            .password("password1")
            .firstName("User1edited")
            .lastName("Last1edited")
            .gender(Gender.MALE)
            .role(Role.USER)
            .birthDate(LocalDate.of(2000, 1, 1))
            .build();
    private static final UserEditDto EDITED_USER_EDIT_DTO = UserEditDto.builder()
            .id(1L)
            .email("user1@exampleedited.com")
            .firstName("User1edited")
            .lastName("Last1edited")
            .gender(Gender.MALE)
            .birthDate(LocalDate.of(2000, 1, 1))
            .build();


    @Test
    @Transactional
    void getAllUsersTest() {
        int exceptedResult = 10;
        int actualResult = userService.getAllUsers().size();

        assertEquals(exceptedResult, actualResult);
    }

    @Test
    @Transactional
    void findUserByIdTest() {
        Optional<UserReadDto> actualResult = userService.findUserById(EXISTED_DTO_ID);
        assertTrue(actualResult.isPresent());

        UserReadDto actualDto = actualResult.get();

        assertAll(
                () -> assertEquals(EXISTED_USER_CREATE_DTO.getEmail(), actualDto.getEmail()),
                () -> assertEquals(EXISTED_USER_CREATE_DTO.getFirstName(), actualDto.getFirstName()),
                () -> assertEquals(EXISTED_USER_CREATE_DTO.getLastName(), actualDto.getLastName()),
                () -> assertEquals(EXISTED_USER_CREATE_DTO.getGender(), actualDto.getGender()),
                () -> assertEquals(EXISTED_USER_CREATE_DTO.getRole(), actualDto.getRole())
        );
    }

    @Test
    @Transactional
    void saveUserTest() throws EmailAlreadyExistsException {
        var saveResult = userService.saveUser(NEW_USER_CREATE_DTO);
        assertTrue(saveResult.isPresent());
    }

    @Test
    @Transactional
    void saveUserWhichAlreadyExists() {
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.saveUser(EXISTED_USER_CREATE_DTO);
        });
    }

    @Test
    @Transactional
    void updateUserTest() {
        var actualResult = userService.updateUser(1L, EDITED_USER_CREATE_EDIT_DTO);
        assertTrue(actualResult.isPresent());

        var resultDto = actualResult.get();
        assertAll(
                () -> assertEquals(EDITED_USER_CREATE_EDIT_DTO.getEmail(), resultDto.getEmail()),
                () -> assertEquals(EDITED_USER_CREATE_EDIT_DTO.getFirstName(), resultDto.getFirstName()),
                () -> assertEquals(EDITED_USER_CREATE_EDIT_DTO.getLastName(), resultDto.getLastName())
        );
    }

    @Test
    @Transactional
    void deleteUserTest() {
        var exceptedResult = 9;
        userService.deleteUser(EXISTED_DTO_ID);
        var actualResult = userService.getAllUsers().size();
        assertEquals(exceptedResult, actualResult);
    }

    @Test
    @Transactional
    void loadUserByUsernameTest() {
        var actualResult = userService.loadUserByUsername(EXISTED_USER_CREATE_DTO.getEmail());
        assertEquals(EXISTED_USER_CREATE_DTO.getEmail(), actualResult.getUsername());
        assertEquals(EXISTED_USER_CREATE_DTO.getPassword(), actualResult.getPassword());
    }

    @Test
    @Transactional
    void loadUserUsingANonExistentUsernameTest() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(NEW_USER_CREATE_DTO.getEmail());
        });
    }

    @Test
    @Transactional
    void findUserByEmailTest() {
        var dtoResult = userService.findUserByEmail(EXISTED_USER_CREATE_DTO.getEmail());

        assertThat(dtoResult)
                .isPresent()
                .map(UserReadDto::getEmail)
                .contains(EXISTED_USER_CREATE_DTO.getEmail());

    }

    @Test
    @Transactional
    void updateProfileTest() {
        var actualResult = userService.updateProfile(EXISTED_DTO_ID, EDITED_USER_EDIT_DTO);

        assertTrue(actualResult.isPresent());

        var resultDto = actualResult.get();
        assertAll(
                () -> assertEquals(EDITED_USER_EDIT_DTO.getEmail(), resultDto.getEmail()),
                () -> assertEquals(EDITED_USER_EDIT_DTO.getFirstName(), resultDto.getFirstName()),
                () -> assertEquals(EDITED_USER_EDIT_DTO.getLastName(), resultDto.getLastName())
        );
    }

}
