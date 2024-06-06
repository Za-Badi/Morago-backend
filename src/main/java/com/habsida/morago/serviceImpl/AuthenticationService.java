package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.inputs.LoginUserDto;
import com.habsida.morago.model.inputs.RegisterUserDto;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
//import com.habsida.morago.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    public User signup(RegisterUserDto input) throws Exception {
        if (userRepository.findByPhone(input.getPhone()).isPresent()) {
            throw new Exception("Phone number is already used: " + input.getPhone());
        }
        User user = new User();
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        if (input.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (String roleInput : input.getRoles()) {
                Role role = roleRepository.findByName(roleInput)
                        .orElseThrow(() -> new Exception("Role not found with name: " + roleInput));
                roles.add(role);
            }
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getPhone(),
                        input.getPassword()
                )
        );
        return userRepository.findByPhone(input.getPhone())
                .orElseThrow();
    }
}