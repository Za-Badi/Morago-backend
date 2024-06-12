package com.habsida.morago.service;

import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id) throws Exception;
    public User getAuthenticatedUser();
    public User addUser(UserInput userInput) throws Exception;
    public User updateUser(Long id, UserInput userInput) throws Exception;
    public  void deleteUser(Long id) throws Exception;
    public String resetPassword(String token, String newPassword);
    public User updatePassword(String originalPassword,String newPassword);
    public boolean changeIsActive(Long id);
    public boolean changeIsDebtor(Long id);
    public User addFcmToken(String fcmToken, Long id) throws Exception;
    public void deleteFcmToken(Long id) throws Exception;
    public User addApnToken(String apnToken, Long id) throws Exception;
    public void deleteApnToken(Long id) throws Exception;
}