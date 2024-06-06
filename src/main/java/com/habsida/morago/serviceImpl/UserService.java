package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.util.List;
import java.util.Set;


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


    public User getUserByPhone(String phone) throws Exception {
        return repository.findByPhone(phone)
                .orElseThrow(() -> new Exception("User not found with phone: " + phone));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
    public User addUser(UserInput userDto) {
        User user = new User();
        user.setPhone(userDto.getPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("ROLE_TRANSLATOR")) {
                user.setTranslatorProfile(new TranslatorProfile());
            }
            else if (roles.contains("ROLE_USER")) {
                user.setUserProfile(new UserProfile());
            }
        }
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
        if (userDto.getPhone() != null && !userDto.getPhone().trim().isEmpty()) { user.setPhone(userDto.getPhone()); }
        if (userDto.getPassword() != null && !userDto.getPassword().trim().isEmpty()) { user.setPassword(passwordEncoder.encode(userDto.getPassword())); }
        if (userDto.getFirstName() != null && !userDto.getFirstName().trim().isEmpty()) { user.setFirstName(userDto.getFirstName()); }
        if (userDto.getLastName() != null && !userDto.getLastName().trim().isEmpty()) { user.setLastName(userDto.getLastName()); }
        return repository.save(user);
    }

    public void deleteUser(Long id) throws Exception {
        repository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        repository.deleteById(id);
    }

    public Boolean validatePassword(String password, String anotherPassword) {
        return passwordEncoder.matches(anotherPassword, password);
    }
    public String resetPassword(String token, String newPassword) {
//        Still to be implemented
        return "Password has been changed";
    }






}