package com.habsida.morago.service;

import com.habsida.morago.model.dto.RoleDTO;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> getAllRoles();

    void add(RoleDTO roleDTO);

    Optional<RoleDTO> getRoleById(Long id);
}
