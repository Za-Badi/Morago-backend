package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserService userService;
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    public User getUserById(Long id) throws ExceptionGraphql {
        return userService.getUserById(id);
    }
    public User getUserByPhone(String phone) throws ExceptionGraphql {
        return userService.getUserByPhone(phone);
    }
    public User getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }
    public Boolean existsUserByPhone(String phone) {
        return userService.existsUserByPhone(phone);
    }
    public UserPage getAllUsersPaged(Integer page, Integer size) {
        return userService.getAllUsersPaged(page, size);
    }
    public UserPage searchUsers(String searchInput, Integer page, Integer size) {
        return userService.searchUsers(searchInput, page, size);
    }
    @PreAuthorize("isAuthenticated()")
    public List<User> testAuthentication() {
        return userService.getAllUsers();
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> testUser() {
        return userService.getAllUsers();
    }
    @PreAuthorize("hasRole('ROLE_TRANSLATOR')")
    public List<User> testTranslator() {
        return userService.getAllUsers();
    }

}
