package com.habsida.morago.resolver;


//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import graphql.kickstart.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class PasswordResolver {



    private final UserService userService;

    @MutationMapping
    public User updatePassword(@Argument String originalPassword, @Argument String newPassword) {
        return userService.updatePassword(originalPassword, newPassword);
    }
    @MutationMapping
    public String resetPasswordRequest(@Argument String token,@Argument String username) {
        return userService.resetPassword(token,username);
    }
}
