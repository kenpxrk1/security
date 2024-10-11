package com.kenpxrk.security.service.impl;

import com.kenpxrk.security.dto.RegisterFormDto;
import com.kenpxrk.security.dto.UserUpdateDto;
import com.kenpxrk.security.exception.PasswordsDontMatchException;
import com.kenpxrk.security.exception.RoleNotFoundException;
import com.kenpxrk.security.exception.UserNotFoundException;
import com.kenpxrk.security.mapper.UserMapper;
import com.kenpxrk.security.model.UserEntity;
import com.kenpxrk.security.repository.RoleRepository;
import com.kenpxrk.security.repository.UserRepository;
import com.kenpxrk.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(RegisterFormDto registerFormDto) {
        if (!registerFormDto.getPassword().equals(registerFormDto.getPasswordConfirm())) {
            throw new PasswordsDontMatchException();
        }
        registerFormDto.setPassword(bCryptPasswordEncoder.encode(registerFormDto.getPassword()));
        UserEntity userEntity = mapper.registerFormToUserEntity(registerFormDto);
        userEntity.setRoles(Set.of(roleRepository.findByName("ROLE_USER").orElseThrow(RoleNotFoundException::new)));
        userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public void updateUser(UserUpdateDto userUpdateDto, Long id) {
        UserEntity userFromDb = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        mapper.updateUserEntityFromDto(userUpdateDto, userFromDb);
        userRepository.save(userFromDb);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
