package com.kenpxrk.security.service.impl;

import com.kenpxrk.security.model.UserEntity;
import com.kenpxrk.security.repository.UserRepository;
import com.kenpxrk.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(UserEntity user, Long id) {
        UserEntity userFromDb = userRepository.findById(id).orElseThrow();
        userFromDb.setAge(user.getAge());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setRoles(user.getRoles());
        userFromDb.setUsername(user.getUsername());
        userFromDb.setPassword(user.getPassword());
        userRepository.save(userFromDb);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).get();
    }
}
