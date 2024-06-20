package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserService userService;
    @Autowired
    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    public User getUserById(Long id) throws Exception {
        return userService.getUserById(id);
    }
    public User getUserByPhone(String phone) throws Exception {
        return userService.getUserByPhone(phone);
    }
    public User getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }


}