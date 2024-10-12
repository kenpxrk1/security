package com.kenpxrk.security.service.impl;

import com.kenpxrk.security.model.RoleEntity;
import com.kenpxrk.security.repository.RoleRepository;
import com.kenpxrk.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }
}
