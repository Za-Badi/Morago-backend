package com.habsida.morago.service;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


//   Commented this to remove the error

//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.repository = userRepository;
//    }
//    public UserService(UserRepository userRepository) {
//        this.repository = userRepository;
//    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }
    public User saveUser(User user) {
        return repository.save(user);
    }
    @Transactional
    public User updatePassword(String originalPassword, String newPassword ){
        User user = getCurrentUser();
        if(passwordEncoder.matches(originalPassword, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
//            throw new Exception());
        }
        return user;
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
//      still to be implemented
        return new User();
    }
    public Boolean validatePassword(String password, String anotherPassword) {
        return passwordEncoder.matches(anotherPassword, password);
    }
    public String resetPassword(String token, String newPassword) {
//        Still to be implemented
        return "Password has been changed";
    }




}
