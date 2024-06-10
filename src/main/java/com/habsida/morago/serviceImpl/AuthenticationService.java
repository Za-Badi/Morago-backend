package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.repository.FileRepository;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final FileRepository fileRepository;


    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            FileRepository fileRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.fileRepository = fileRepository;
    }

    public void signUpAsUser(UserInput userInput) throws Exception {
        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
            throw new Exception("Phone number is already used: " + userInput.getPhone());
        }
        User user = new User();
        user.setPhone(userInput.getPhone());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setBalance((double) 0);
        user.setRatings((double) 0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new Exception("Role not found with name: ROLE_USER"));
        roles.add(userRole);
        if (userInput.getRoles() != null) {
            for (String roleName : userInput.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new Exception("Role not found with name: " + roleName));
                roles.add(role);
            }
        }
        user.setRoles(roles);
        user.setUserProfile(new UserProfile());
        userRepository.save(user);
    }

    public void signUpAsTranslator(UserInput userInput) throws Exception {
        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
            throw new Exception("Phone number is already used: " + userInput.getPhone());
        }
        User user = new User();
        user.setPhone(userInput.getPhone());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setBalance((double) 0);
        user.setRatings((double) 0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        List<Role> roles = new ArrayList<>();
        Role translatorRole = roleRepository.findByName("ROLE_TRANSLATOR")
                .orElseThrow(() -> new Exception("Role not found with name: ROLE_TRANSLATOR"));
        roles.add(translatorRole);
        if (userInput.getRoles() != null) {
            for (String roleName : userInput.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new Exception("Role not found with name: " + roleName));
                roles.add(role);
            }
        }
        user.setRoles(roles);
        user.setTranslatorProfile(new TranslatorProfile());
        userRepository.save(user);
    }

    public User logIn(UserInput userInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userInput.getPhone(),
                        userInput.getPassword()
                )
        );
        return userRepository.findByPhone(userInput.getPhone())
                .orElseThrow();
    }
}