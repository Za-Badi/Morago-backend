//package com.habsida.morago.resolver;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.inputs.SearchUsersInput;
//import com.habsida.morago.model.inputs.UserInput;
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.inputs.UserPage;
//import com.habsida.morago.serviceImpl.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.List;
//
//
//@Component
//@RequiredArgsConstructor
//public class UserResolver {
//    private final UserService userService;
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//    public User getUserById(Long id) throws GlobalException {
//        return userService.getUserById(id);
//    }
//    public User getUserByPhone(String phone) throws GlobalException {
//        return userService.getUserByPhone(phone);
//    }
//    public User getAuthenticatedUser() {
//        return userService.getAuthenticatedUser();
//    }
//    public Boolean existsUserByPhone(String phone) {
//        return userService.existsUserByPhone(phone);
//    }
//    public User addUser(UserInput userInput) throws GlobalException {
//        return userService.addUser(userInput);
//    }
//    public User updateUser(Long id, UserInput userInput) throws GlobalException {
//        return userService.updateUser(id, userInput);
//    }
//    public Boolean deleteUser(Long id) throws GlobalException {
//        userService.deleteUser(id);
//        return true;
//    }
//    public User changeIsActive(Long id, Boolean isActive) throws GlobalException {
//        return userService.changeIsActive(id, isActive);
//    }
//    public User changeIsDebtor(Long id, Boolean isDebtor) throws GlobalException  {
//        return userService.changeIsDebtor(id, isDebtor);
//    }
//    public User addFcmToken(String fcmToken, Long id) throws GlobalException {
//        return userService.addFcmToken(fcmToken, id);
//    }
//    public Boolean deleteFcmToken(Long id) throws GlobalException {
//        userService.deleteFcmToken(id);
//        return true;
//    }
//    public User addApnToken(String apnToken, Long id) throws GlobalException {
//        return userService.addApnToken(apnToken, id);
//    }
//    public Boolean deleteApnToken(Long id) throws GlobalException {
//        userService.deleteApnToken(id);
//        return true;
//    }
//    public UserPage getAllUsersPaged(Integer page, Integer size) {
//        return userService.getAllUsersPaged(page, size);
//    }
//    public UserPage searchUsers(SearchUsersInput searchUsersInput, Integer page, Integer size) {
//        return userService.searchUsers(searchUsersInput, page, size);
//    }
//}