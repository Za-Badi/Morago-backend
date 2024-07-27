package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import lombok.Builder;
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
@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public User signUpAsUser(RegisterUserInput registerUserInput) throws ExceptionGraphql {
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
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role newRole = Role.builder().name("USER").build();
                    return roleRepository.save(newRole);
                });
        User user = User.builder()
                .phone(phoneInput)
                .password(passwordEncoder.encode(registerUserInput.getPassword()))
                .image(null)
                .translatorProfile(null)
                .userProfile(new UserProfile())
                .roles(new ArrayList<>(List.of(userRole)))
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User signUpAsTranslator(RegisterUserInput registerUserInput) throws ExceptionGraphql {
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
        Role translatorRole = roleRepository.findByName("TRANSLATOR")
                .orElseGet(() -> {
                    Role newRole = Role.builder().name("TRANSLATOR").build();
                    return roleRepository.save(newRole);
                });
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .languages(new ArrayList<>())
                .themes(new ArrayList<>())
                .build();
        User user = User.builder()
                .phone(phoneInput)
                .password(passwordEncoder.encode(registerUserInput.getPassword()))
                .image(null)
                .userProfile(null)
                .translatorProfile(translatorProfile)
                .roles(new ArrayList<>(List.of(translatorRole)))
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User signUpAsConsultant(RegisterUserInput registerUserInput) throws ExceptionGraphql {
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
        Role consultantRole = roleRepository.findByName("CONSULTANT")
                .orElseGet(() -> {
                    Role newRole = Role.builder().name("CONSULTANT").build();
                    return roleRepository.save(newRole);
                });
        ConsultantProfile consultantProfile = ConsultantProfile.builder()
                .build();
        User user = User.builder()
                .phone(phoneInput)
                .password(passwordEncoder.encode(registerUserInput.getPassword()))
                .image(null)
                .userProfile(null)
                .consultantProfile(consultantProfile)
                .roles(new ArrayList<>(List.of(consultantRole)))
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User logIn(LoginUserInput loginUserInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserInput.getPhone(),
                        loginUserInput.getPassword()
                )
        );
        return userRepository.findByPhone(loginUserInput.getPhone())
                .orElseThrow();
    }
}
