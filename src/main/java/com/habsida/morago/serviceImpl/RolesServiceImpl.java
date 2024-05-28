package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Roles;
import com.habsida.morago.repository.RolesRepository;
import com.habsida.morago.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl  implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Optional<Roles> getRoleById(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public void add(Roles role) {
        rolesRepository.save(role);
    }
}
