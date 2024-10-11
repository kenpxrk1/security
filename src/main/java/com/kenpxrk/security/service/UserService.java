package com.kenpxrk.security.service;

import com.kenpxrk.security.dto.RegisterFormDto;
import com.kenpxrk.security.dto.UserUpdateDto;
import com.kenpxrk.security.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {
    UserEntity findUserById(Long id);

    UserEntity findUserByUsername(String username);

    List<UserEntity> findAllUsers();

    void saveUser(RegisterFormDto registerFormDto);

    void updateUser(UserUpdateDto userUpdateDto, Long id);

    void deleteUser(Long id);

}
