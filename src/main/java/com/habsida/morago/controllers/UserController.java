package com.habsida.morago.controllers;



import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.SearchUsersInput;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.resolver.UserResolver;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserResolver userResolver;
    @QueryMapping
    public List<User> getAllUsers() {
        return userResolver.getAllUsers();
    }
    @QueryMapping
    public UserPage getAllUsersPaged(@Argument Integer page, @Argument Integer size) {
        return userResolver.getAllUsersPaged(page, size);
    }
    @QueryMapping
    public User getUserById(@Argument Long id) throws GlobalException {
        return userResolver.getUserById(id);
    }
    @QueryMapping
    public User getUserByPhone(@Argument String phone) throws GlobalException {
        return userResolver.getUserByPhone(phone);
    }
    @QueryMapping
    public User getAuthenticatedUser() {
        return userResolver.getAuthenticatedUser();
    }
    @QueryMapping
    public Boolean existsUserByPhone(@Argument String phone) {
        return userResolver.existsUserByPhone(phone);
    }
    @QueryMapping
    public UserPage searchUsers(@Argument SearchUsersInput searchUsersInput, @Argument Integer page, @Argument Integer size) {
        return userResolver.searchUsers(searchUsersInput, page, size);
    }
    @MutationMapping
    public User addUser(@Argument UserInput userInput) throws GlobalException{
        return userResolver.addUser(userInput);
    }
    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput userInput) throws GlobalException {
        return userResolver.updateUser(id, userInput);
    }
    @MutationMapping
    public Boolean deleteUser(@Argument Long id) throws GlobalException {
        return userResolver.deleteUser(id);
    }
    @MutationMapping
    public User changeIsActive(@Argument Long id, @Argument Boolean isActive) throws GlobalException {
        return userResolver.changeIsActive(id, isActive);
    }
    @MutationMapping
    public User changeIsDebtor(@Argument Long id, @Argument Boolean isDebtor) throws GlobalException {
        return userResolver.changeIsDebtor(id, isDebtor);
    }
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<User> testAuthentication() {
        return userResolver.getAllUsers();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @QueryMapping
    public List<User> testUser() {
        return userResolver.getAllUsers();
    }
    @PreAuthorize("hasRole('ROLE_TRANSLATOR')")
    @QueryMapping
    public List<User> testTranslator() {
        return userResolver.getAllUsers();
    }
    @MutationMapping
    public User addFcmToken(@Argument String fcmToken, @Argument Long id) throws GlobalException {
        return userResolver.addFcmToken(fcmToken, id);
    }
    @MutationMapping
    public Boolean deleteFcmToken(@Argument Long id) throws GlobalException {
        return userResolver.deleteFcmToken(id);
    }
    @MutationMapping
    public User addApnToken(@Argument String apnToken, @Argument Long id) throws GlobalException {
        return userResolver.addApnToken(apnToken, id);
    }
    @MutationMapping
    public Boolean deleteApnToken(@Argument Long id) throws GlobalException {
        return userResolver.deleteApnToken(id);
    }
}