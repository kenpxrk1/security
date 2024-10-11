package com.kenpxrk.security.controller;

import com.kenpxrk.security.model.UserEntity;
import com.kenpxrk.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        log.info("Вызван контроллер getProfile для пользователя {}", userDetails.getUsername() );
        UserEntity user = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "user-profile";
    }
}
