package com.novo.personalproject.controller;

import com.novo.personalproject.model.dao.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity getUsers() {
        UserDao dao = UserDao.getInstance();

        try {
            return ResponseEntity.ok("сервер работает " + dao.delete(3L));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
