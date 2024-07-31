package com.habsida.morago.service;

import com.habsida.morago.model.dto.RoleDTO;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> getAllRoles();

    Optional<RoleDTO> getRoleById(Long id);

    void add(String roleName);

    void deleteRoleById(Long id);
}
