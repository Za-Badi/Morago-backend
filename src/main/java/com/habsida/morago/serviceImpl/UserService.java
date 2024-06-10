package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
    }


    public User getUserByPhone(String phone) throws Exception {
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new Exception("User not found with phone: " + phone));
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userRepository.findByPhone(userDetails.getUsername())
                    .orElseThrow();
        }
        return null;
    }
    public Boolean existsUserByPhone(String phone) {
        return userRepository.findByPhone(phone).isPresent();
    }

    public User addUser(UserInput userInput) throws Exception {
        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
            throw new Exception("Phone number is already used: " + userInput.getPhone());
        }
        User user = new User();
        user.setPhone(userInput.getPhone());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setBalance((double)0);
        user.setRatings((double)0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(0);
        List<Role> roles = new ArrayList<>();
        if (userInput.getRoles() != null) {
            for (String roleName : userInput.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new Exception("Role not found with name: " + roleName));
                roles.add(role);
            }
            if (roles.contains("ROLE_TRANSLATOR")) {
                user.setTranslatorProfile(new TranslatorProfile());
            }
            else if (roles.contains("ROLE_USER")) {
                user.setUserProfile(new UserProfile());
            }
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserInput userInput) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        if (userInput.getPhone() != null && !userInput.getPhone().trim().isEmpty()) { user.setPhone(userInput.getPhone()); }
        if (userInput.getPassword() != null && !userInput.getPassword().trim().isEmpty()) { user.setPassword(passwordEncoder.encode(userInput.getPassword())); }
        if (userInput.getFirstName() != null && !userInput.getFirstName().trim().isEmpty()) { user.setFirstName(userInput.getFirstName()); }
        if (userInput.getLastName() != null && !userInput.getLastName().trim().isEmpty()) { user.setLastName(userInput.getLastName()); }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws Exception {
        userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        userRepository.deleteById(id);
    }
    public boolean changeIsActive(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        user.setIsActive(!user.getIsActive());
        return true;
    }
    public boolean changeIsDebtor(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        user.setIsDebtor(!user.getIsDebtor());
        return true;
    }

    public Boolean validatePassword(String password, String anotherPassword) {
        return passwordEncoder.matches(anotherPassword, password);
    }
    public String resetPassword(String token, String newPassword) {
//        Still to be implemented
        return "Password has been changed";
    }
    @Transactional
    public User updatePassword(String originalPassword, String newPassword ){
        User user = getAuthenticatedUser();
        if(passwordEncoder.matches(originalPassword, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
//            throw new Exception());
        }
        return user;
    }
}