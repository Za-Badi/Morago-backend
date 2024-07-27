package com.habsida.morago;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testSignUpAsUser() throws ExceptionGraphql {
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

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authenticationService.signUpAsUser(registerUserInput);

        assertNotNull(result);
        assertEquals(user.getPhone(), registerUserInput.getPhone());
        assertEquals(user.getPassword(), encodedPassword);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSignUpAsTranslator() throws ExceptionGraphql {
        RegisterUserInput registerUserInput = new RegisterUserInput();
        registerUserInput.setPhone("0110");
        registerUserInput.setPassword("password");

        Role translatorRole = new Role();
        translatorRole.setName("TRANSLATOR");

        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setLanguages(new ArrayList<>());
        translatorProfile.setThemes(new ArrayList<>());

        User user = User.builder()
                .phone("0110")
                .password("encodedPassword")
                .image(null)
                .userProfile(null)
                .translatorProfile(translatorProfile)
                .roles(new ArrayList<>(List.of(translatorRole)))
                .build();

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(translatorRole));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authenticationService.signUpAsTranslator(registerUserInput);

        assertNotNull(result);
        assertEquals(user.getPhone(), registerUserInput.getPhone());
        assertEquals(user.getPassword(), "encodedPassword");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSignUpAsConsultant() throws ExceptionGraphql {
        RegisterUserInput registerUserInput = new RegisterUserInput();
        registerUserInput.setPhone("0110");
        registerUserInput.setPassword("password");

        Role consultantRole = new Role();
        consultantRole.setName("CONSULTANT");

        ConsultantProfile consultantProfile = new ConsultantProfile();
        consultantProfile.setLanguages(new ArrayList<>());
        consultantProfile.setThemes(new ArrayList<>());

        User user = User.builder()
                .phone("0110")
                .password("encodedPassword")
                .image(null)
                .userProfile(null)
                .translatorProfile(null)
                .consultantProfile(consultantProfile)
                .roles(new ArrayList<>(List.of(consultantRole)))
                .build();

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(consultantRole));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authenticationService.signUpAsConsultant(registerUserInput);

        assertNotNull(result);
        assertEquals(user.getPhone(), registerUserInput.getPhone());
        assertEquals(user.getPassword(), "encodedPassword");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLogIn() {
        LoginUserInput loginUserInput = new LoginUserInput();
        loginUserInput.setPhone("0110");
        loginUserInput.setPassword("password");

        User user = User.builder()
                .phone("0110")
                .password("encodedPassword")
                .build();

        doAnswer(invocation -> null).when(authenticationManager).authenticate(
                any(UsernamePasswordAuthenticationToken.class)
        );
        when(userRepository.findByPhone(anyString())).thenReturn(Optional.of(user));

        User result = authenticationService.logIn(loginUserInput);

        assertNotNull(result);
        assertEquals(user.getPhone(), loginUserInput.getPhone());
        assertEquals(user.getPassword(), "encodedPassword");
        verify(userRepository, times(1)).findByPhone(anyString());
    }
}
