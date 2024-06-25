package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.RoleDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository rolesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository rolesRepository, ModelMapper modelMapper) {
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper;
    }

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
    public void add(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        rolesRepository.save(role);
    }
}
