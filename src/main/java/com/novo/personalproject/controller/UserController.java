package com.novo.personalproject.controller;

import com.novo.personalproject.dto.UserCreateEditDto;
import com.novo.personalproject.dto.UserEditDto;
import com.novo.personalproject.dto.UserReadDto;
import com.novo.personalproject.error.EmailAlreadyExistsException;
import com.novo.personalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<UserReadDto>> getUsers() {
        List<UserReadDto> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
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
        return "page/registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") @Validated UserCreateEditDto userCreateEditDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", userCreateEditDto);
            return "redirect:/face/users/registration";
        }

        try {
            userService.saveUser(userCreateEditDto);
        } catch (EmailAlreadyExistsException ex) {
            bindingResult.addError(new ObjectError("userCreateEditDto", ex.getMessage()));
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", userCreateEditDto);
            return "redirect:/face/users/registration";
        }
        System.out.println("controller after saveUser");
        return "page/success";
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @ModelAttribute("user") @Validated UserEditDto user,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        return userService.updateProfile(id, user)
                .map(dto -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(dto))
                .orElse(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        if (!userService.deleteUser(id)) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok()
                    .build();
        }
    }
}
