package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
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

    public String signUpAsUser(RegisterUserInput registerUserInput) throws Exception {
        authenticationService.signUpAsUser(registerUserInput);
        LoginUserInput loginUserInput = new LoginUserInput();
        loginUserInput.setPhone(registerUserInput.getPhone());
        loginUserInput.setPassword(registerUserInput.getFirstPassword());
        User authenticatedUser = authenticationService.logIn(loginUserInput);
        return jwtService.generateToken(authenticatedUser);
    }

    public String signUpAsTranslator(RegisterUserInput registerUserInput) throws Exception {
        authenticationService.signUpAsTranslator(registerUserInput);
        LoginUserInput loginUserInput = new LoginUserInput();
        loginUserInput.setPhone(registerUserInput.getPhone());
        loginUserInput.setPassword(registerUserInput.getFirstPassword());
        User authenticatedUser = authenticationService.logIn(loginUserInput);
        return jwtService.generateToken(authenticatedUser);
    }

    public String logIn(LoginUserInput loginUserInput) {
        User authenticatedUser = authenticationService.logIn(loginUserInput);
        return jwtService.generateToken(authenticatedUser);
    }

}