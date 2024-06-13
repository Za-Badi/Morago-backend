package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.UserRepositoryPaged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryPaged userRepositoryPaged;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRepositoryPaged userRepositoryPaged) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepositoryPaged = userRepositoryPaged;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public UserPage getAllUsersPaged(Integer page) {
        int pageSize = 10;
        Page<User> usersPage = userRepositoryPaged.findAll(PageRequest.of(page, pageSize));
        return new UserPage(
                usersPage.getContent(),
                usersPage.getTotalPages(),
                (int) usersPage.getTotalElements(),
                usersPage.getSize(),
                usersPage.getNumber()
        );
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
        user.setImage(null);
        user.setTranslatorProfile(null);
        user.setUserProfile(null);
        List<Role> roles = new ArrayList<>();
        user.setRoles(roles);
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.getRoles().clear();
        userRepository.save(user);
        userRepository.deleteById(id);
    }
    public boolean changeIsActive(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
        return true;
    }
    public boolean changeIsDebtor(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.setIsDebtor(!user.getIsDebtor());
        userRepository.save(user);
        return true;
    }
    public User addFcmToken(String fcmToken, Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.setFcmToken(fcmToken);
        return userRepository.save(user);
    }
    public void deleteFcmToken(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.setFcmToken(null);
        userRepository.save(user);
    }
    public User addApnToken(String apnToken, Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.setApnToken(apnToken);
        return userRepository.save(user);
    }
    public void deleteApnToken(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        user.setApnToken(null);
        userRepository.save(user);
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