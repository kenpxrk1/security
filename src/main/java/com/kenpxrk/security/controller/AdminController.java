package com.kenpxrk.security.controller;

import com.kenpxrk.security.dto.RegisterFormDto;
import com.kenpxrk.security.dto.UserUpdateDto;
import com.kenpxrk.security.service.RoleService;
import com.kenpxrk.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAll());
        log.info("roles: {}", roleService.findAll());
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
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit-roles/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editRolesForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleService.findAll());
        return "edit-user-roles";
    }

    @PostMapping("/users/edit-roles/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editRoles(@PathVariable Long id, @RequestParam List<String> roles) {
        userService.updateUserRoles(id, roles);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addForm(Model model) {
        model.addAttribute("user", new RegisterFormDto());
        return "add-user-form";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("registerForm") @Valid RegisterFormDto registerFormDto, Model model) {
        userService.saveUser(registerFormDto);
        return "redirect:/admin/users";
    }
}
