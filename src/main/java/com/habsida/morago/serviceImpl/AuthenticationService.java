package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
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
    public void signUpAsUser(UserInput userInput) throws Exception {
        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
            throw new Exception("Phone number is already used: " + userInput.getPhone());
        }
        User user = new User();
        user.setPhone(userInput.getPhone());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setBalance((double)0);
        user.setRatings((double)0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        if (userInput.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (String roleInput : userInput.getRoles()) {
                Role role = roleRepository.findByName(roleInput)
                        .orElseThrow(() -> new Exception("Role not found with name: " + roleInput));
                roles.add(role);
            }
            user.setRoles(roles);
        }
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
        user.setBalance((double)0);
        user.setRatings((double)0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        if (userInput.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (String roleInput : userInput.getRoles()) {
                Role role = roleRepository.findByName(roleInput)
                        .orElseThrow(() -> new Exception("Role not found with name: " + roleInput));
                roles.add(role);
            }
            user.setRoles(roles);
        }
        user.setTranslatorProfile(new TranslatorProfile());
        userRepository.save(user);
    }
    public User authenticate(UserInput userInput) {
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