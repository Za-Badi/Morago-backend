package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Role;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository rolesRepository;

    @Override
    public List<Role> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public void add(Role role) {
        rolesRepository.save(role);
    }
}