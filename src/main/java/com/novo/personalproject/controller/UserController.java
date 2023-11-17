package com.novo.personalproject.controller;

import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.model.entity.User;
import com.novo.personalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        List<UserReadDto> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable(value = "id") Long id) {
        UserReadDto user = userService.findUserById(id).get();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String getForm(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        return "form";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") UserCreateEditDto user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        UserReadDto user = userService.findUserById(id).get();
        model.addAttribute("user", user);
        return "edit-form";
    }

    @PatchMapping ("/{id}")
    public String editUser(@PathVariable long id, @ModelAttribute("user") UserCreateEditDto user) {
        userService.updateUser(id,user);
        return "redirect:/users/" + id;
    }

}
