package com.habsida.morago;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.Role;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AuthenticationService authenticationService;
    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testSignUpAsUser_Success() throws ExceptionGraphql {
        RegisterUserInput registerUserInput = new RegisterUserInput();
        registerUserInput.setPhone("0110");
        registerUserInput.setPassword("password");
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.empty());
        Role userRole = new Role();
        userRole.setName("USER");
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(userRole));
        User user = User.builder()
                .phone("0110")
                .password(encodedPassword)
                .image(null)
                .translatorProfile(null)
                .userProfile(new UserProfile())
                .roles(new ArrayList<>(List.of(userRole)))
                .build();
        UserDTO userDTO = new UserDTO();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);
        UserDTO result = authenticationService.signUpAsUser(registerUserInput);
        assertNotNull(result);
        assertEquals(user.getPhone(), registerUserInput.getPhone());
        assertEquals(user.getPassword(), encodedPassword);
        verify(userRepository, times(1)).save(any(User.class));
    }
}