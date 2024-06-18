package com.habsida.morago.service;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id) throws GlobalException;
    public User getAuthenticatedUser();
    public User addUser(UserInput userInput) throws GlobalException;
    public User updateUser(Long id, UserInput userInput) throws GlobalException;
    public  void deleteUser(Long id) throws GlobalException;
    public String resetPassword(String token, String newPassword);
    public User updatePassword(String originalPassword,String newPassword);
    public User changeIsActive(Long id, Boolean isActive) throws GlobalException;
    public User changeIsDebtor(Long id, Boolean isDebtor) throws GlobalException;
    public User addFcmToken(String fcmToken, Long id) throws GlobalException;
    public void deleteFcmToken(Long id) throws GlobalException;
    public User addApnToken(String apnToken, Long id) throws GlobalException;
    public void deleteApnToken(Long id) throws GlobalException;
}