package com.habsida.morago.resolver;


//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import graphql.kickstart.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
