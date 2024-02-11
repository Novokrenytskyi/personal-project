package com.novo.personalproject.controller;

import com.novo.personalproject.dto.UserInfo;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserSessionRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/session")
    public ResponseEntity<UserReadDto> getUserAuthoritiesFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        return userService.findUserByEmail(name)
                .map(content -> ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
