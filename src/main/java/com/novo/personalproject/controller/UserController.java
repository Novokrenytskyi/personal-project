package com.novo.personalproject.controller;

import com.novo.personalproject.model.dao.UserDao;
import com.novo.personalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        List<User> users = userDao.findAll();
        System.out.println(users.size());
        try {
            return ResponseEntity.ok("сервер работает " + users.size());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello-world";
    }

}
