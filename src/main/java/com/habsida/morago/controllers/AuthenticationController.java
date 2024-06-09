package com.habsida.morago.controllers;

import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.resolver.AuthenticationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationController {
    private final AuthenticationResolver authenticationResolver;

    public AuthenticationController(AuthenticationResolver authenticationResolver) {
        this.authenticationResolver = authenticationResolver;
    }
    @MutationMapping
    public String signUpAsUser(@Argument UserInput userInput) throws Exception {
        return authenticationResolver.signUpAsUser(userInput);
    }
    @MutationMapping
    public String signUpAsTranslator(@Argument UserInput userInput) throws Exception {
        return authenticationResolver.signUpAsTranslator(userInput);
    }
    @MutationMapping
    public String authenticate(@Argument UserInput userInput) {
        return authenticationResolver.authenticate(userInput);
    }
}