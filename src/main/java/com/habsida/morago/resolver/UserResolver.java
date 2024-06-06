package com.habsida.morago.resolver;

import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class UserResolver {
    private final UserService userService;
    @Autowired
    public UserResolver(UserService userService) {
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
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }
    public User addUser(UserInput userDto) {
        return userService.addUser(userDto);
    }
    public User updateUser(Long id, UserInput userDto) throws Exception {
        return userService.updateUser(id, userDto);
    }
    public Boolean deleteUser(Long id) throws Exception {
        userService.deleteUser(id);
        return true;
    }
}