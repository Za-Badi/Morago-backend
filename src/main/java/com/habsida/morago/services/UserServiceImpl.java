package com.habsida.morago.services;

import com.habsida.morago.dtos.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
    }

    public User addUser(UserInput userDto) {
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserInput userDto) throws Exception {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found with id: " + id));
        if (userDto.getPhone() != null) { user.setPhone(userDto.getPhone()); }
        if (userDto.getPassword() != null) { user.setPassword(passwordEncoder.encode(userDto.getPassword())); }
        if (userDto.getFirstName() != null) { user.setFirstName(userDto.getFirstName()); }
        if (userDto.getLastName() != null) { user.setLastName(userDto.getLastName()); }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws Exception {
        userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found with id: " + id));
        userRepository.deleteById(id);
    }
}
