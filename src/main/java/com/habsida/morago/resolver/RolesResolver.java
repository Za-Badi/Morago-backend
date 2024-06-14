package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Role;
import com.habsida.morago.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolesResolver {
    private final RoleService rolesService;

    public List<Role> getAllRoles() {
        return rolesService.getAllRoles();
    }
    public Role getRole(Long id) {
        return rolesService.getRoleById(id).orElseThrow(()-> new RuntimeException("resource not found"));
    }

    public void createRoles(Role roles) {
        rolesService.add(roles);
    }
}