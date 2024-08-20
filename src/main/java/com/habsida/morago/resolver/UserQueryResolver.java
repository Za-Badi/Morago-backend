package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.model.inputs.UsersAndWithdrawals;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserService userService;

    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    public UserDTO getUserById(Long id) throws ExceptionGraphql {
        return userService.getUserById(id);
    }

    public UserDTO getUserByPhone(String phone) throws ExceptionGraphql {
        return userService.getUserByPhone(phone);
    }

    public UserDTO getAuthenticatedUser() {
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
    public List<UserDTO> testAuthentication() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public List<UserDTO> testUser() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_TRANSLATOR')")
    public List<UserDTO> testTranslator() {
        return userService.getAllUsers();
    }

    public PageOutput<UsersAndWithdrawals> getUsersAndWithdrawals(PagingInput pagingInput) {
        return userService.getUsersAndWithdrawals(pagingInput);
    }
}
