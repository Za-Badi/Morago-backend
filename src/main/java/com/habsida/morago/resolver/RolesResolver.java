package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Roles;
import com.habsida.morago.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolesResolver {
    private final RolesService rolesService;

    public List<Roles> getAllRoles() {
        return rolesService.getAllRoles();
    }
    public Roles getRole(Long id) {
        return rolesService.getRoleById(id).orElseThrow(()-> new RuntimeException("resource not found"));
    }

    public void createRoles(Roles roles) {
        rolesService.add(roles);
    }
}