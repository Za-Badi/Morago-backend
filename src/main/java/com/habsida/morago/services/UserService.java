package com.habsida.morago.services;

import com.habsida.morago.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(String id) throws Exception;
    public User addUser(User user);
    public User updateUser(String id, User userUpdate) throws Exception;
    public void deleteUser(String id) throws Exception;
}
