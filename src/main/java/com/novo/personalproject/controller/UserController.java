package com.novo.personalproject.controller;

import com.novo.personalproject.model.dao.UserDao;
import com.novo.personalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String getUsers(Model model) {
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable(value = "id") Long id) {
        User user = userDao.findById(id).get();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userDao.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userDao.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        User user = userDao.findById(id).get();
        model.addAttribute("user", user);
        return "edit-form";
    }

    @PatchMapping ("/{id}")
    public String editUser(@PathVariable long id, @ModelAttribute("user") User user) {
        userDao.update(user);
        return "redirect:/users/" + id;
    }

}
