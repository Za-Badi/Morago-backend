package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.model.inputs.UsersAndWithdrawals;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.UserRepositoryPaged;
import com.habsida.morago.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryPaged userRepositoryPaged;
    private final WithdrawalRepository withdrawalRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserPage getAllUsersPaged(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        Page<User> usersPage = userRepositoryPaged.findAll(PageRequest.of(page, size));
        List<UserDTO> userDTOs = usersPage.getContent().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new UserPage(
                userDTOs,
                usersPage.getTotalPages(),
                (int) usersPage.getTotalElements(),
                usersPage.getSize(),
                usersPage.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserByPhone(String phone) throws ExceptionGraphql {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ExceptionGraphql("User not found with phone: " + phone));
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional(readOnly = true)
    public UserDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails userDetails) {
            User user = userRepository.findByPhone(userDetails.getUsername())
                    .orElseThrow();
            return modelMapper.map(user, UserDTO.class);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public UserPage searchUsers(String searchInput, Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        Page<User> usersPage = userRepositoryPaged.searchUsersByPhoneOrFirstNameOrLastName(searchInput, PageRequest.of(page, size));
        List<UserDTO> userDTOs = usersPage.getContent().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new UserPage(
                userDTOs,
                usersPage.getTotalPages(),
                (int) usersPage.getTotalElements(),
                usersPage.getSize(),
                usersPage.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public Boolean existsUserByPhone(String phone) {
        return userRepository.findByPhone(phone).isPresent();
    }

    @Transactional
    public UserDTO addUser(UserInput userInput) throws ExceptionGraphql {
        if (userInput.getPhone().isBlank() || userInput.getPassword().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        if (userRepository.findByPhone(userInput.getPhone()).isPresent()) {
            throw new ExceptionGraphql("Phone number is already used: " + userInput.getPhone());
        }
        User user = new User();
        user.setPhone(phoneInput);
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setImage(null);
        user.setTranslatorProfile(null);
        user.setUserProfile(null);
        List<Role> roles = new ArrayList<>();
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserInput userInput) throws ExceptionGraphql {
        if (userInput.getPhone() == null || userInput.getPhone().isBlank() ||
                userInput.getPassword() == null || userInput.getPassword().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        if (userRepository.findByPhone(userInput.getPhone()).isPresent() && !Objects.equals(userInput.getPhone(), user.getPhone())) {
            throw new ExceptionGraphql("Phone number is already used: " + userInput.getPhone());
        }
        user.setPhone(phoneInput);
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        if (userInput.getFirstName() != null && !userInput.getFirstName().isBlank()) {
            user.setFirstName(userInput.getFirstName());
        }
        if (userInput.getLastName() != null && !userInput.getLastName().isBlank()) {
            user.setLastName(userInput.getLastName());
        }
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Transactional
    public void deleteUser(Long id) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.getRoles().clear();
        userRepository.save(user);
        userRepository.deleteById(id);
    }

    @Transactional
    public Boolean changeIsActive(Long id, Boolean isActive) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setIsActive(isActive);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public Boolean changeIsDebtor(Long id, Boolean isDebtor) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setIsDebtor(isDebtor);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public UserDTO addFcmToken(String fcmToken, Long id) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setFcmToken(fcmToken);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Transactional
    public void deleteFcmToken(Long id) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setFcmToken(null);
        userRepository.save(user);
    }

    @Transactional
    public UserDTO addApnToken(String apnToken, Long id) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setApnToken(apnToken);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Transactional
    public void deleteApnToken(Long id) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setApnToken(null);
        userRepository.save(user);
    }

    public String resetPassword(String token, String newPassword) {
        // Still to be implemented
        return "Password has been changed";
    }

    @Transactional
    public UserDTO updatePassword(String originalPassword, String newPassword) {
        User user = modelMapper.map(getAuthenticatedUser(), User.class);
        if (passwordEncoder.matches(originalPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            // throw new Exception());
        }
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    public List<UsersAndWithdrawals> getUsersAndWithdrawals() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    List<Withdrawals> withdrawals = withdrawalRepository.findByUserId(user.getId());
                    List<WithdrawalsDTO> withdrawalsDTOs = withdrawals.stream()
                            .map(withdrawal -> modelMapper.map(withdrawal, WithdrawalsDTO.class))
                            .collect(Collectors.toList());
                    if (!withdrawalsDTOs.isEmpty()) {
                        return new UsersAndWithdrawals(modelMapper.map(user, UserDTO.class), withdrawalsDTOs);
                    }
                    return null;
                })
                .filter(usersAndWithdrawals -> usersAndWithdrawals != null)
                .collect(Collectors.toList());
    }
}
