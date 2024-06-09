package com.habsida.morago.controllers;



import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.resolver.UserResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class UserController {
    private final UserResolver userResolver;
    public UserController(UserResolver userResolver) {
        this.userResolver = userResolver;
    }
    @QueryMapping
    public List<User> getAllUsers() {
        return userResolver.getAllUsers();
    }
    @QueryMapping
    public User getUserById(@Argument Long id) throws Exception {
        return userResolver.getUserById(id);
    }
    @QueryMapping
    public User getUserByPhone(@Argument String phone) throws Exception {
        return userResolver.getUserByPhone(phone);
    }
    @QueryMapping
    public User getCurrentUser() {
        return userResolver.getCurrentUser();
    }
    @MutationMapping
    public User addUser(@Argument UserInput userInput) throws Exception{
        return userResolver.addUser(userInput);
    }
    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput userInput) throws Exception {
        return userResolver.updateUser(id, userInput);
    }
    @MutationMapping
    public Boolean deleteUser(@Argument Long id) throws Exception {
        return userResolver.deleteUser(id);
    }
}