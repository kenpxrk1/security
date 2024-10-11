package com.kenpxrk.security.controller;

import com.kenpxrk.security.dto.UserUpdateDto;
import com.kenpxrk.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit-user-form";
    }

    @PostMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUser(@ModelAttribute UserUpdateDto user, @PathVariable Long id) {
        userService.updateUser(user, id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
