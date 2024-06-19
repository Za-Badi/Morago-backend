//package com.habsida.morago.controllers;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.inputs.LoginUserInput;
//import com.habsida.morago.model.inputs.RegisterUserInput;
//import com.habsida.morago.resolver.AuthenticationResolver;
//import lombok.RequiredArgsConstructor;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//public class AuthenticationController {
//    private final AuthenticationResolver authenticationResolver;
//
//    @MutationMapping
//    public String signUpAsUser(@Argument RegisterUserInput registerUserInput) throws GlobalException {
//        return authenticationResolver.signUpAsUser(registerUserInput);
//    }
//
//    @MutationMapping
//    public String signUpAsTranslator(@Argument RegisterUserInput registerUserInput) throws GlobalException {
//        return authenticationResolver.signUpAsTranslator(registerUserInput);
//    }
//
//    @MutationMapping
//    public String logIn(@Argument LoginUserInput loginUserInput) {
//        return authenticationResolver.logIn(loginUserInput);
//    }
//}