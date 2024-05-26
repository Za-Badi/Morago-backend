package com.habsida.morago.services;

import com.habsida.morago.dtos.LoginUserDto;
import com.habsida.morago.dtos.RegisterUserDto;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setPhone(input.getPhone());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
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