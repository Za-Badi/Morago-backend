package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.RoleDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository rolesRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        return rolesRepository.findAll().stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> getRoleById(Long id) {
        return rolesRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDTO.class));
    }

    @Override
    public void add(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        rolesRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        Role role = rolesRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Role not found with id: " + id));
        role.getUsers().clear();
        rolesRepository.save(role);
        rolesRepository.deleteById(id);
    }
}
