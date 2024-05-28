package com.habsida.morago.service;

import com.habsida.morago.model.entity.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    List<Roles> getAllRoles();

    void add(Roles role);

    Optional<Roles> getRoleById(Long id);
}
