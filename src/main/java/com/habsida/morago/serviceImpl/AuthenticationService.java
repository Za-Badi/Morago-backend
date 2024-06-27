package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public UserDTO signUpAsUser(RegisterUserInput registerUserInput) throws ExceptionGraphql {
        if (registerUserInput.getPhone() == null || registerUserInput.getPhone().isBlank() ||
                registerUserInput.getPassword() == null || registerUserInput.getPassword().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = registerUserInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        if (userRepository.findByPhone(registerUserInput.getPhone()).isPresent()) {
            throw new ExceptionGraphql("Phone number is already used: " + registerUserInput.getPhone());
        }
        User user = new User();
        user.setPhone(phoneInput);
        user.setPassword(passwordEncoder.encode(registerUserInput.getPassword()));
        user.setImage(null);
        user.setTranslatorProfile(null);
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("USER");
                    return roleRepository.save(newRole);
                });
        roles.add(userRole);
        user.setRoles(roles);
        user.setUserProfile(new UserProfile());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    public UserDTO signUpAsTranslator(RegisterUserInput registerUserInput) throws ExceptionGraphql {
        if (registerUserInput.getPhone() == null || registerUserInput.getPhone().isBlank() ||
                registerUserInput.getPassword() == null || registerUserInput.getPassword().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = registerUserInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        if (userRepository.findByPhone(registerUserInput.getPhone()).isPresent()) {
            throw new ExceptionGraphql("Phone number is already used: " + registerUserInput.getPhone());
        }
        User user = new User();
        user.setPhone(phoneInput);
        user.setPhone(registerUserInput.getPhone());
        user.setPassword(passwordEncoder.encode(registerUserInput.getPassword()));
        user.setImage(null);
        user.setUserProfile(null);
        List<Role> roles = new ArrayList<>();
        Role translatorRole = roleRepository.findByName("TRANSLATOR")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("TRANSLATOR");
                    return roleRepository.save(newRole);
                });
        roles.add(translatorRole);
        user.setRoles(roles);
        List<Language> languages = new ArrayList<>();
        List<Theme> themes = new ArrayList<>();
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setLanguages(languages);
        translatorProfile.setThemes(themes);
        user.setTranslatorProfile(translatorProfile);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    public UserDTO logIn(LoginUserInput loginUserInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserInput.getPhone(),
                        loginUserInput.getPassword()
                )
        );
        User user = userRepository.findByPhone(loginUserInput.getPhone())
                .orElseThrow();
        return modelMapper.map(user, UserDTO.class);
    }
}
