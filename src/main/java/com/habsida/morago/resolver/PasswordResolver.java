package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class PasswordResolver {


    private final UserService userService;


    public User updatePassword( String originalPassword,  String newPassword) {
        return userService.updatePassword(originalPassword, newPassword);
    }

    public String resetPasswordRequest( String token,  String username) {
        return userService.resetPassword(token, username);
    }
}
