package com.habsida.morago.resolver;


import com.habsida.morago.model.dto.RoleDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolesResolver {
    private final RoleService rolesService;

    public List<RoleDTO> getAllRoles() {
        return rolesService.getAllRoles();
    }
    public RoleDTO getRole(Long id) {
        return rolesService.getRoleById(id).orElseThrow(()-> new RuntimeException("resource not found"));
    }

    public void createRoles(RoleDTO roles) {
        rolesService.add(roles);
    }
}