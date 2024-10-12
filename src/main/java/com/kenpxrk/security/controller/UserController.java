package com.kenpxrk.security.controller;

import com.kenpxrk.security.model.UserEntity;
import com.kenpxrk.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserEntity user = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "user-profile";
    }
}
