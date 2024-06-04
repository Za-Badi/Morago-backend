package com.habsida.morago.service;

import com.habsida.morago.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();

    void add(Role role);

    Optional<Role> getRoleById(Long id);
}