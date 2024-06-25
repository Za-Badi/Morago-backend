package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.serviceImpl.AuthenticationService;
import com.habsida.morago.serviceImpl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationMutationResolver implements GraphQLMutationResolver {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public String signUpAsUser(RegisterUserInput registerUserInput) throws ExceptionGraphql {
        UserDTO user = authenticationService.signUpAsUser(registerUserInput);
        LoginUserInput loginUserInput = new LoginUserInput();
        loginUserInput.setPhone(user.getPhone());
        loginUserInput.setPassword(registerUserInput.getPassword());
        UserDTO authenticatedUser = authenticationService.logIn(loginUserInput);
        return jwtService.generateToken(authenticatedUser);
    }

    public String signUpAsTranslator(RegisterUserInput registerUserInput) throws ExceptionGraphql {
        UserDTO user = authenticationService.signUpAsTranslator(registerUserInput);
        LoginUserInput loginUserInput = new LoginUserInput();
        loginUserInput.setPhone(user.getPhone());
        loginUserInput.setPassword(registerUserInput.getPassword());
        UserDTO authenticatedUser = authenticationService.logIn(loginUserInput);
        return jwtService.generateToken(authenticatedUser);
    }

    public String logIn(LoginUserInput loginUserInput) {
        UserDTO authenticatedUser = authenticationService.logIn(loginUserInput);
        return jwtService.generateToken(authenticatedUser);
    }
}
