//package com.habsida.morago.resolver;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.inputs.LoginUserInput;
//import com.habsida.morago.model.inputs.RegisterUserInput;
//import com.habsida.morago.model.inputs.UserInput;
//import com.habsida.morago.serviceImpl.AuthenticationService;
//import com.habsida.morago.serviceImpl.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AuthenticationResolver {
//    private final AuthenticationService authenticationService;
//    private final JwtService jwtService;
//
//    public String signUpAsUser(RegisterUserInput registerUserInput) throws GlobalException {
//        User user = authenticationService.signUpAsUser(registerUserInput);
//        LoginUserInput loginUserInput = new LoginUserInput();
//        loginUserInput.setPhone(user.getPhone());
//        loginUserInput.setPassword(registerUserInput.getPassword());
//        User authenticatedUser = authenticationService.logIn(loginUserInput);
//        return jwtService.generateToken(authenticatedUser);
//    }
//
//    public String signUpAsTranslator(RegisterUserInput registerUserInput) throws GlobalException {
//        User user = authenticationService.signUpAsTranslator(registerUserInput);
//        LoginUserInput loginUserInput = new LoginUserInput();
//        loginUserInput.setPhone(user.getPhone());
//        loginUserInput.setPassword(registerUserInput.getPassword());
//        User authenticatedUser = authenticationService.logIn(loginUserInput);
//        return jwtService.generateToken(authenticatedUser);
//    }
//
//    public String logIn(LoginUserInput loginUserInput) {
//        User authenticatedUser = authenticationService.logIn(loginUserInput);
//        return jwtService.generateToken(authenticatedUser);
//    }
//
//}