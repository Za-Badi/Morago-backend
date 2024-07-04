package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.UserInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    public void setUp() {
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
}