//package com.habsida.morago.serviceImpl;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.entity.Role;
//import com.habsida.morago.model.inputs.SearchUsersInput;
//import com.habsida.morago.model.inputs.UserInput;
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.inputs.UserPage;
//import com.habsida.morago.repository.UserRepository;
//import com.habsida.morago.repository.UserRepositoryPaged;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.Authentication;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepositoryPaged userRepositoryPaged;
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//    public UserPage getAllUsersPaged(Integer page, Integer size) {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
//        Page<User> usersPage = userRepositoryPaged.findAll(PageRequest.of(page, size));
//        return new UserPage(
//                usersPage.getContent(),
//                usersPage.getTotalPages(),
//                (int) usersPage.getTotalElements(),
//                usersPage.getSize(),
//                usersPage.getNumber()
//        );
//    }
//
//    public User getUserById(Long id) throws GlobalException {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//    }
//
//
//    public User getUserByPhone(String phone) throws GlobalException {
//        return userRepository.findByPhone(phone)
//                .orElseThrow(() -> new GlobalException("User not found with phone: " + phone));
//    }
//
//    public User getAuthenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {
//            return userRepository.findByPhone(userDetails.getUsername())
//                    .orElseThrow();
//        }
//        return null;
//    }
//    public UserPage searchUsers(SearchUsersInput searchUsersInput, Integer page, Integer size) {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
//        String phone = searchUsersInput.getPhone();
//        String firstName = searchUsersInput.getFirstName();
//        Page<User> usersPage = userRepositoryPaged.findByPhoneContainingAndFirstNameContainingIgnoreCase(phone, firstName, PageRequest.of(page, size));
//        return new UserPage(
//                usersPage.getContent(),
//                usersPage.getTotalPages(),
//                (int) usersPage.getTotalElements(),
//                usersPage.getSize(),
//                usersPage.getNumber()
//        );
//    }
//    public Boolean existsUserByPhone(String phone) {
//        return userRepository.findByPhone(phone).isPresent();
//    }
//
//    public User addUser(UserInput userInput) throws GlobalException {
//        if (userInput.getPhone().isBlank() || userInput.getPassword().isBlank()) {
//            throw new GlobalException("Empty values are not allowed");
//        }
//        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
//        if (phoneInput.isBlank()) {
//            throw new GlobalException("Phone number must contain at least one digit");
//        }
//        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
//            throw new GlobalException("Phone number is already used: " + userInput.getPhone());
//        }
//        User user = new User();
//        user.setPhone(phoneInput);
//        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
//        user.setFirstName(userInput.getFirstName());
//        user.setLastName(userInput.getLastName());
//        user.setImage(null);
//        user.setTranslatorProfile(null);
//        user.setUserProfile(null);
//        List<Role> roles = new ArrayList<>();
//        user.setRoles(roles);
//        return userRepository.save(user);
//    }
//
//    public User updateUser(Long id, UserInput userInput) throws GlobalException {
//        if (userInput.getPhone() == null || userInput.getPhone().isBlank() ||
//                userInput.getPassword() == null || userInput.getPassword().isBlank()) {
//            throw new GlobalException("Empty values are not allowed");
//        }
//        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
//        if (phoneInput.isBlank()) {
//            throw new GlobalException("Phone number must contain at least one digit");
//        }
//        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
//            throw new GlobalException("Phone number is already used: " + userInput.getPhone());
//        }
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setPhone(phoneInput);
//        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
//        if (userInput.getFirstName() != null && !userInput.getFirstName().isBlank()) { user.setFirstName(userInput.getFirstName()); }
//        if (userInput.getLastName() != null && !userInput.getLastName().isBlank()) { user.setLastName(userInput.getLastName()); }
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(Long id) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.getRoles().clear();
//        userRepository.save(user);
//        userRepository.deleteById(id);
//    }
//    public User changeIsActive(Long id, Boolean isActive) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setIsActive(isActive);
//        return userRepository.save(user);
//    }
//    public User changeIsDebtor(Long id, Boolean isDebtor) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setIsDebtor(isDebtor);
//        return userRepository.save(user);
//    }
//    public User addFcmToken(String fcmToken, Long id) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setFcmToken(fcmToken);
//        return userRepository.save(user);
//    }
//    public void deleteFcmToken(Long id) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setFcmToken(null);
//        userRepository.save(user);
//    }
//    public User addApnToken(String apnToken, Long id) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setApnToken(apnToken);
//        return userRepository.save(user);
//    }
//    public void deleteApnToken(Long id) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        user.setApnToken(null);
//        userRepository.save(user);
//    }
//    public String resetPassword(String token, String newPassword) {
////        Still to be implemented
//        return "Password has been changed";
//    }
//    @Transactional
//    public User updatePassword(String originalPassword, String newPassword ){
//        User user = getAuthenticatedUser();
//        if(passwordEncoder.matches(originalPassword, user.getPassword())){
//            user.setPassword(passwordEncoder.encode(newPassword));
//        } else {
////            throw new Exception());
//        }
//        return user;
//    }
//}