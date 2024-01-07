package com.novo.personalproject.controller;

import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/face/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getUsers(Model model) {
        List<UserReadDto> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable(value = "id") Long id) {
        return userService.findUserById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "user";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") UserCreateEditDto user) {
        return "registration";
    }

    @PostMapping ("/registration")
    public String createUser(@ModelAttribute("user") @Validated UserCreateEditDto userCreateEditDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", userCreateEditDto);
            return "redirect:/face/users/registration";
        }

        userService.saveUser(userCreateEditDto);
        return "redirect:/face/login";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        return userService.findUserById(id).
                map(user -> {
                    model.addAttribute("user", user);
                    return "edit-form";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute("user") @Validated UserEditDto user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/face/users/{id}/edit";
        }

        System.out.println(user.getFirstName());

       return userService.updateProfile(id, user)
               .map(it -> "redirect:/face/profile")
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        if (!userService.deleteUser(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
}
