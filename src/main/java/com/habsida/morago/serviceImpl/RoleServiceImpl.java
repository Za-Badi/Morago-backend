package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.dto.RoleDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.service.RoleService;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository rolesRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public PageOutput<RoleDTO> getAllRoles(PagingInput pagingInput) {
        Page<Role> roles = rolesRepository.findAll(PageUtil.buildPageable(pagingInput));
        return new PageOutput<>(
                roles.getNumber(),
                roles.getTotalPages(),
                roles.getTotalElements(),
                roles.getContent().stream()
                        .map(withdrawals -> modelMapperUtil.map(roles, RoleDTO.class))
                        .collect(Collectors.toList())
        );
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
