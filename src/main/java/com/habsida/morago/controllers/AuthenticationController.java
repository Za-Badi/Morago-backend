//package com.habsida.morago.controllers;
//
//import com.habsida.morago.model.inputs.LoginUserInput;
//import com.habsida.morago.model.inputs.RegisterUserInput;
//import com.habsida.morago.model.inputs.UserInput;
//import com.habsida.morago.resolver.AuthenticationResolver;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class AuthenticationController {
//    private final AuthenticationResolver authenticationResolver;
//
//    public AuthenticationController(AuthenticationResolver authenticationResolver) {
//        this.authenticationResolver = authenticationResolver;
//    }
//
//    @MutationMapping
//    public String signUpAsUser(@Argument RegisterUserInput registerUserInput) throws Exception {
//        return authenticationResolver.signUpAsUser(registerUserInput);
//    }
//
//    @MutationMapping
//    public String signUpAsTranslator(@Argument RegisterUserInput registerUserInput) throws Exception {
//        return authenticationResolver.signUpAsTranslator(registerUserInput);
//    }
//
//    @MutationMapping
//    public String logIn(@Argument LoginUserInput loginUserInput) {
//        return authenticationResolver.logIn(loginUserInput);
//    }
//}