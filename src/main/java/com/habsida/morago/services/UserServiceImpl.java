package com.habsida.morago.services;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> getUserById(String id) throws Exception {
        Long userId = Long.parseLong(id);
        Optional<User> optionalUserProfile = userRepository.findById(userId);
        if (optionalUserProfile.isPresent()) {
            return userRepository.findById(userId);
        } else {
            throw new Exception("User not found for id: " + id);
        }
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User userUpdate) throws Exception {
        Long userId = Long.parseLong(id);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPhone(userUpdate.getPhone());
            user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(userUpdate.getLastName());
            user.setBalance(userUpdate.getBalance());
            user.setFcmToken(userUpdate.getFcmToken());
            user.setApnToken(userUpdate.getApnToken());
            user.setRatings(userUpdate.getRatings());
            user.setTotalRatings(userUpdate.getTotalRatings());
            user.setIsActive(userUpdate.getIsActive());
            user.setIsDebtor(userUpdate.getIsDebtor());
            user.setOnBoardingStatus(userUpdate.getOnBoardingStatus());
            return userRepository.save(user);
        } else {
            throw new Exception("User not found for id: " + id);
        }
    }

    public void deleteUser(String id) throws Exception {
        Long userId = Long.parseLong(id);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new Exception("User not found for id: " + id);
        }
    }
}
