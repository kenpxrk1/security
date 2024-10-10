package com.kenpxrk.security.service;

import com.kenpxrk.security.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    UserEntity findUserById(Long id);

    List<UserEntity> findAllUsers();

    void saveUser(UserEntity user);

    void updateUser(UserEntity user, Long id);

    void deleteUser(Long id);
}
