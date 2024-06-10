package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.serviceImpl.AuthenticationService;
import com.habsida.morago.serviceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationResolver {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationResolver(JwtService jwtService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    public String signUpAsUser(UserInput userInput) throws Exception {
        authenticationService.signUpAsUser(userInput);
        User authenticatedUser = authenticationService.logIn(userInput);
        return jwtService.generateToken(authenticatedUser);
    }

    public String signUpAsTranslator(UserInput userInput) throws Exception {
        authenticationService.signUpAsTranslator(userInput);
        User authenticatedUser = authenticationService.logIn(userInput);
        return jwtService.generateToken(authenticatedUser);
    }

    public String logIn(UserInput userInput) {
        User authenticatedUser = authenticationService.logIn(userInput);
        return jwtService.generateToken(authenticatedUser);
    }

}