package com.habsida.morago.services;

import com.habsida.morago.dtos.UserInput;
import com.habsida.morago.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(Long id) throws Exception;
    public User addUser(UserInput userDto);
    public User updateUser(Long id, UserInput userDto) throws Exception;
    public  void deleteUser(Long id) throws Exception;
}
