package com.habsida.morago.resolver;


import com.habsida.morago.model.dto.RoleDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolesResolver {
    private final RoleService rolesService;

    public PageOutput<RoleDTO> getAllRoles(PagingInput pagingInput) {
        return rolesService.getAllRoles(pagingInput);
    }

    public RoleDTO getRole(Long id) {
        return rolesService.getRoleById(id).orElseThrow(()-> new RuntimeException("resource not found"));
    }

    public void createRoles(String roles) {
        rolesService.add(roles);
    }

    public void deleteRoleById(Long roleId) {
        rolesService.deleteRoleById(roleId);
    }
}