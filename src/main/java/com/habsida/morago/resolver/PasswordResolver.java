package com.habsida.morago.resolver;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@RequiredArgsConstructor
@Controller
public class PasswordResolver implements GraphQLMutationResolver {



    private final UserService userService;


    public User updatePassword(String originalPassword, String newPassword) {
        return userService.updatePassword(originalPassword, newPassword);
    }

    public String resetPasswordRequest(String token, String username) {
        return userService.resetPassword(token,username);
    }
}
