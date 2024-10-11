package com.kenpxrk.security.config;

import com.kenpxrk.security.model.RoleEntity;
import com.kenpxrk.security.model.UserEntity;
import com.kenpxrk.security.repository.RoleRepository;
import com.kenpxrk.security.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Initializer {
    private static final RoleEntity adminRole = new RoleEntity(1L,"ROLE_ADMIN");
    private static final RoleEntity userRole = new RoleEntity(2L,"ROLE_USER");

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    public void init(){
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()){
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("ROLE_USER").isEmpty()){
            roleRepository.save(userRole);
        }

        if (userRepository.findByUsername("admin").isEmpty()){
            UserEntity admin = new UserEntity("admin", "admin@mail.ru", 21,
                    encoder.encode("admin"));
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);
        }

    }
}
