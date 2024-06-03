package com.habsida.morago.serviceImpl;

import com.habsida.morago.dtos.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
    }



    public User addUser(UserInput userDto) {
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
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
    public User updateUser(Long id, UserInput userDto) throws Exception {
        User user = repository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        if (userDto.getPhone() != null) { user.setPhone(userDto.getPhone()); }
        if (userDto.getPassword() != null) { user.setPassword(passwordEncoder.encode(userDto.getPassword())); }
        if (userDto.getFirstName() != null) { user.setFirstName(userDto.getFirstName()); }
        if (userDto.getLastName() != null) { user.setLastName(userDto.getLastName()); }
        return repository.save(user);
    }

    public void deleteUser(Long id) throws Exception {
        repository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        repository.deleteById(id);
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