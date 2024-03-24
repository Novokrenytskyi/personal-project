package com.novo.personalproject.controller;

import com.novo.personalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/face")
public class FaceController {
    @Autowired
    UserService userService;

    @GetMapping
    public String facePage() {
        return "page/face";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "page/login";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.findUserByEmail(userDetails.getUsername())
                .map(user -> {
                    model.addAttribute("user", user);
                    return "page/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/admin")
    //    TODO: Fix page access
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "page/admin";
    }

    @GetMapping("/product")
    public String productPage() {
        return "page/product";
    }


    @GetMapping("/all-users")
//    TODO: Fix page access
    @PreAuthorize("hasRole('ADMIN')")
    public String usersPage() {
        return "page/users";
    }

    @GetMapping("/edit")
    public String getEditForm(@RequestParam("id") Long id) {
        if (id == null || id < 0) {
            return "redirect:/face";
        }
        return "page/edit-form";

    }
}
