package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.model.inputs.UserPage;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.UserRepositoryPaged;
import com.habsida.morago.serviceImpl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRepositoryPaged userRepositoryPaged;
    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    public void setUp() {
        SecurityContextHolder.setContext(securityContext);
    }
//    @Test
//    public void testGetAllUsers() {
//        User userFirst = User.builder().phone("0110").password("encodedPassword0110").build();
//        User userSecond = User.builder().phone("0120").password("encodedPassword20120").build();
//        List<User> users = Arrays.asList(userFirst, userSecond);
//        UserDTO userFirstDTO = new UserDTO();
//        UserDTO userSecondDTO = new UserDTO();
//        List<UserDTO> userDTOs = Arrays.asList(userFirstDTO, userSecondDTO);
//        when(userRepository.findAll()).thenReturn(users);
//        when(modelMapper.map(userFirst, UserDTO.class)).thenReturn(userFirstDTO);
//        when(modelMapper.map(userSecond, UserDTO.class)).thenReturn(userSecondDTO);
//        List<UserDTO> result = userService.getAllUsers();
//        assertNotNull(result);
//        assertEquals(userDTOs.size(), result.size());
//        assertEquals(userFirstDTO, result.get(0));
//        assertEquals(userSecondDTO, result.get(1));
//        verify(userRepository, times(1)).findAll();
//    }
    @Test
    public void testGetAllUsersPaged() {
        User user1 = User.builder().phone("0110").password("encodedPassword0110").build();
        User user2 = User.builder().phone("0120").password("encodedPassword0120").build();
        List<User> users = Arrays.asList(user1, user2);
        Page<User> userPage = new PageImpl<>(users, PageRequest.of(0, 10), users.size());
        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);
        when(userRepositoryPaged.findAll(PageRequest.of(0, 10))).thenReturn(userPage);
        when(modelMapper.map(user1, UserDTO.class)).thenReturn(userDTO1);
        when(modelMapper.map(user2, UserDTO.class)).thenReturn(userDTO2);
        UserPage result = userService.getAllUsersPaged(0, 10);
        assertNotNull(result);
        assertEquals(userDTOs.size(), result.getContent().size());
        assertEquals(userPage.getTotalPages(), result.getTotalPages());
        assertEquals((int) userPage.getTotalElements(), result.getTotalElements());
        assertEquals(userPage.getSize(), result.getSize());
        assertEquals(userPage.getNumber(), result.getNumber());
        assertEquals(userDTO1, result.getContent().get(0));
        assertEquals(userDTO2, result.getContent().get(1));
        verify(userRepositoryPaged, times(1)).findAll(PageRequest.of(0, 10));
    }
    @Test
    public void testGetUserById() throws ExceptionGraphql {
        User user = User.builder().phone("0110").password("encodedPassword").build();
        user.setId(1L);
        UserDTO userDTO = new UserDTO();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        UserDTO result = userService.getUserById(1L);
        assertNotNull(result);
        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(user, UserDTO.class);
    }
    @Test
    public void testGetUserByPhone() throws ExceptionGraphql {
        User user = User.builder().phone("0110").password("encodedPassword").build();
        UserDTO userDTO = new UserDTO();
        when(userRepository.findByPhone("0110")).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        UserDTO result = userService.getUserByPhone("0110");
        assertNotNull(result);
        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findByPhone("0110");
        verify(modelMapper, times(1)).map(user, UserDTO.class);
    }
    @Test
    public void testGetAuthenticatedUser() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("0110");
        User user = User.builder().phone("0110").password("encodedPassword").build();
        UserDTO userDTO = new UserDTO();
        when(userRepository.findByPhone("0110")).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        UserDTO result = userService.getAuthenticatedUser();
        assertNotNull(result);
        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findByPhone("0110");
        verify(modelMapper, times(1)).map(user, UserDTO.class);
    }
    @Test
    public void testSearchUsers() {
        String searchInput = "Rixio";
        Integer page = 0;
        Integer size = 10;
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).firstName("Rixio").phone("0110").build());
        users.add(User.builder().id(2L).firstName("Ivana").phone("0120").build());
        Page<User> userPage = new PageImpl<>(users, PageRequest.of(page, size), users.size());
        when(userRepositoryPaged.searchUsersByPhoneOrFirstNameOrLastName(searchInput, PageRequest.of(page, size)))
                .thenReturn(userPage);
        List<UserDTO> userDTOs = users.stream()
                .map(user -> UserDTO.builder().id(user.getId()).firstName(user.getFirstName()).phone(user.getPhone()).build())
                .collect(Collectors.toList());
        when(modelMapper.map(any(User.class), eq(UserDTO.class)))
                .thenReturn(UserDTO.builder().id(1L).firstName("Rixio").phone("0110").build());
        UserPage result = userService.searchUsers(searchInput, page, size);
        assertEquals(userDTOs.size(), result.getContent().size());
        assertEquals(userPage.getTotalPages(), result.getTotalPages());
        assertEquals((int) userPage.getTotalElements(), result.getTotalElements());
        assertEquals(userPage.getSize(), result.getSize());
        assertEquals(userPage.getNumber(), result.getNumber());
        verify(userRepositoryPaged, times(1)).searchUsersByPhoneOrFirstNameOrLastName(searchInput, PageRequest.of(page, size));
        verify(modelMapper, times(users.size())).map(any(User.class), eq(UserDTO.class));
    }
    @Test
    public void testExistsUserByPhone() {
        String existingPhone = "0110";
        String nonExistingPhone = "0120";
        when(userRepository.findByPhone(existingPhone)).thenReturn(Optional.ofNullable(new User()));
        when(userRepository.findByPhone(nonExistingPhone)).thenReturn(Optional.empty());
        boolean existsExistingPhone = userService.existsUserByPhone(existingPhone);
        assertEquals(true, existsExistingPhone);
        boolean existsNonExistingPhone = userService.existsUserByPhone(nonExistingPhone);
        assertEquals(false, existsNonExistingPhone);
    }
    @Test
    public void testAddUser() throws ExceptionGraphql {
        UserInput userInput = new UserInput();
        userInput.setPhone("0110");
        userInput.setPassword("password");
        userInput.setFirstName("Rixio");
        userInput.setLastName("Morales");
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.empty());
        User user = User.builder()
                .phone("0110")
                .password(encodedPassword)
                .firstName("Rixio")
                .lastName("Morales")
                .image(null)
                .translatorProfile(null)
                .userProfile(null)
                .roles(new ArrayList<>())
                .build();
        UserDTO userDTO = new UserDTO();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);
        UserDTO result = userService.addUser(userInput);
        assertNotNull(result);
        assertEquals(user.getPhone(), userInput.getPhone());
        assertEquals(user.getPassword(), encodedPassword);
        assertEquals(user.getFirstName(), userInput.getFirstName());
        assertEquals(user.getLastName(), userInput.getLastName());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    public void testUpdateUser() throws ExceptionGraphql {
        User existingUser = User.builder()
                .id(1L)
                .firstName("Rixio")
                .lastName("Morales")
                .phone("0120")
                .password("password0120")
                .build();
        UserInput userInput = UserInput.builder()
                .firstName("Rixio")
                .lastName("Morales")
                .phone("0110")
                .password("password0110")
                .build();
        User updatedUser = User.builder()
                .id(1L)
                .firstName("Rixio")
                .lastName("Morales")
                .phone("0110")
                .password("encodedpassword")
                .build();
        UserDTO expectedUserDTO = UserDTO.builder()
                .id(1L)
                .firstName("Rixio")
                .lastName("Morales")
                .phone("0110")
                .build();
        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(modelMapper.map(updatedUser, UserDTO.class)).thenReturn(expectedUserDTO);
        UserDTO result = userService.updateUser(existingUser.getId(), userInput);
        assertNotNull(result);
        assertEquals(expectedUserDTO.getFirstName(), result.getFirstName());
        assertEquals(expectedUserDTO.getLastName(), result.getLastName());
        assertEquals(expectedUserDTO.getPhone(), result.getPhone());
        verify(userRepository, times(1)).findById(existingUser.getId());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    public void testDeleteUser() throws ExceptionGraphql {
        Role userRole = new Role();
        userRole.setName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        User userDelete = User.builder()
                .id(1L)
                .firstName("Rixio")
                .lastName("Morales")
                .phone("0110")
                .password("password")
                .roles(roles)
                .build();
        when(userRepository.findById(userDelete.getId())).thenReturn(Optional.of(userDelete));
        userService.deleteUser(userDelete.getId());
        verify(userRepository, times(1)).findById(userDelete.getId());
        verify(userRepository, times(1)).save(userDelete);
        verify(userRepository, times(1)).deleteById(userDelete.getId());
    }
    @Test
    public void testChangeIsActive() throws ExceptionGraphql {
        Long userId = 1L;
        Boolean isActive = true;
        User user = new User();
        user.setId(userId);
        user.setIsActive(!isActive);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Boolean result = userService.changeIsActive(userId, isActive);
        assertTrue(result);
        assertEquals(isActive, user.getIsActive());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testChangeIsDebtor() throws ExceptionGraphql {
        Long userId = 1L;
        Boolean isDebtor = true;
        User user = new User();
        user.setId(userId);
        user.setIsDebtor(!isDebtor);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Boolean result = userService.changeIsDebtor(userId, isDebtor);
        assertTrue(result);
        assertEquals(isDebtor, user.getIsDebtor());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testAddFcmToken() throws ExceptionGraphql {
        Long userId = 1L;
        String fcmToken = "fcmToken";
        User user = new User();
        user.setId(userId);
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFcmToken(fcmToken);
        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(userId);
        expectedUserDTO.setFcmToken(fcmToken);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(updatedUser);
        when(modelMapper.map(updatedUser, UserDTO.class)).thenReturn(expectedUserDTO);
        UserDTO result = userService.addFcmToken(fcmToken, userId);
        assertNotNull(result);
        assertEquals(expectedUserDTO.getId(), result.getId());
        assertEquals(expectedUserDTO.getFcmToken(), result.getFcmToken());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testDeleteFcmToken() throws ExceptionGraphql {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFcmToken("fcmToken");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> userService.deleteFcmToken(userId));
        assertNull(user.getFcmToken());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testAddApnToken() throws ExceptionGraphql {
        Long userId = 1L;
        String apnToken = "apnToken";
        User user = new User();
        user.setId(userId);
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setApnToken(apnToken);
        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(userId);
        expectedUserDTO.setApnToken(apnToken);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(updatedUser);
        when(modelMapper.map(updatedUser, UserDTO.class)).thenReturn(expectedUserDTO);
        UserDTO result = userService.addApnToken(apnToken, userId);
        assertNotNull(result);
        assertEquals(expectedUserDTO.getId(), result.getId());
        assertEquals(expectedUserDTO.getApnToken(), result.getApnToken());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testDeleteApnToken() throws ExceptionGraphql {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setApnToken("apnToken");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> userService.deleteApnToken(userId));
        assertNull(user.getApnToken());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
}