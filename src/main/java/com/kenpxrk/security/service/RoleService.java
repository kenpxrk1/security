package com.kenpxrk.security.service;

import com.kenpxrk.security.model.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> findAll();
}
