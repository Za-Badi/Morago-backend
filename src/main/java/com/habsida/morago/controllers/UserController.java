package com.habsida.morago.controllers;

import com.habsida.morago.dtos.UserInput;
import com.habsida.morago.model.entity.User;

import com.habsida.morago.resolver.UserResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class UserController {
    private final UserResolver userResolver ;
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
    @MutationMapping
    public User addUser(@Argument UserInput userDto) {
        return userResolver.addUser(userDto);
    }
    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput userDto) throws Exception {
        return userResolver.updateUser(id, userDto);
    }
    @MutationMapping
    public Boolean deleteUser(@Argument Long id) throws Exception {
        return userResolver.deleteUser(id);
    }
}