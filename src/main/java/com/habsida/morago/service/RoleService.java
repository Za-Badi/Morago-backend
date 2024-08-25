package com.habsida.morago.service;

import com.habsida.morago.model.dto.RoleDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    PageOutput<RoleDTO> getAllRoles(PagingInput pagingInput);

    Optional<RoleDTO> getRoleById(Long id);

    void add(String roleName);

    void deleteRoleById(Long id);
}
