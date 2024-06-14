package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.repository.FileRepository;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import graphql.GraphQLException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final FileRepository fileRepository;
    private final UserService userService;


    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            FileRepository fileRepository,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    public void signUpAsUser(RegisterUserInput registerUserInput) throws Exception {
        if (userRepository.findByPhone(registerUserInput.getPhone()).isPresent()) {
            throw new GraphQLException("Phone number is already used: " + registerUserInput.getPhone());
        }
        User user = new User();
        user.setPhone(registerUserInput.getPhone());
        if (!Objects.equals(registerUserInput.getFirstPassword(), registerUserInput.getSecondPassword())) {
            throw new GraphQLException("Passwords don't match");
        }
        user.setPassword(passwordEncoder.encode(registerUserInput.getFirstPassword()));
        user.setBalance((double) 0);
        user.setRatings((double) 0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        user.setImage(null);
        user.setTranslatorProfile(null);
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new Exception("Role not found with name: ROLE_USER"));
        roles.add(userRole);
        user.setRoles(roles);
        user.setUserProfile(new UserProfile());
        userRepository.save(user);
    }

    public void signUpAsTranslator(RegisterUserInput registerUserInput) throws Exception {
        if (userRepository.findByPhone(registerUserInput.getPhone()).isPresent()) {
            throw new Exception("Phone number is already used: " + registerUserInput.getPhone());
        }
        User user = new User();
        user.setPhone(registerUserInput.getPhone());
        if (!Objects.equals(registerUserInput.getFirstPassword(), registerUserInput.getSecondPassword())) {
            throw new GraphQLException("Passwords don't match");
        }
        user.setPassword(passwordEncoder.encode(registerUserInput.getFirstPassword()));
        user.setBalance((double) 0);
        user.setRatings((double) 0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        user.setImage(null);
        user.setUserProfile(null);
        List<Role> roles = new ArrayList<>();
        Role translatorRole = roleRepository.findByName("ROLE_TRANSLATOR")
                .orElseThrow(() -> new Exception("Role not found with name: ROLE_TRANSLATOR"));
        roles.add(translatorRole);
        user.setRoles(roles);
        List<Language> languages = new ArrayList<>();
        List<Theme> themes = new ArrayList<>();
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setLanguages(languages);
        translatorProfile.setThemes(themes);
        user.setTranslatorProfile(translatorProfile);
        userRepository.save(user);
    }

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