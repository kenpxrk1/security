package com.kenpxrk.security.controller;

import com.kenpxrk.security.dto.RegisterFormDto;
import com.kenpxrk.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerForm", new RegisterFormDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerForm") @Valid RegisterFormDto registerFormDto, Model model) {
        userService.saveUser(registerFormDto);
        return "redirect:/login";
    }
}
