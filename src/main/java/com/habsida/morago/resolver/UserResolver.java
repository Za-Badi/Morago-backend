package com.habsida.morago.resolver;

import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserPage;
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
    public User getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }
    public Boolean existsUserByPhone(String phone) {
        return userService.existsUserByPhone(phone);
    }
    public User addUser(UserInput userInput) throws Exception {
        return userService.addUser(userInput);
    }
    public User updateUser(Long id, UserInput userInput) throws Exception {
        return userService.updateUser(id, userInput);
    }
    public Boolean deleteUser(Long id) throws Exception {
        userService.deleteUser(id);
        return true;
    }
    public Boolean changeIsActive(Long id) throws Exception  {
        return userService.changeIsActive(id);
    }
    public Boolean changeIsDebtor(Long id) throws Exception  {
        return userService.changeIsDebtor(id);
    }
    public User addFcmToken(String fcmToken, Long id) throws Exception {
        return userService.addFcmToken(fcmToken, id);
    }
    public Boolean deleteFcmToken(Long id) throws Exception {
        userService.deleteFcmToken(id);
        return true;
    }
    public User addApnToken(String apnToken, Long id) throws Exception {
        return userService.addApnToken(apnToken, id);
    }
    public Boolean deleteApnToken(Long id) throws Exception {
        userService.deleteApnToken(id);
        return true;
    }
    public UserPage getAllUsersPaged(Integer page) {
        return userService.getAllUsersPaged(page);
    }
}