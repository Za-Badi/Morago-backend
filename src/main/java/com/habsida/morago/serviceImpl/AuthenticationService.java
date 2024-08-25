package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.inputs.LoginUserInput;
import com.habsida.morago.model.inputs.RegisterTranslatorConsultantInput;
import com.habsida.morago.model.inputs.RegisterUserInput;
import com.habsida.morago.repository.ConsultantProfileRepository;
import com.habsida.morago.repository.RoleRepository;
import com.habsida.morago.repository.TranslatorProfileRepository;
import com.habsida.morago.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final ConsultantProfileRepository consultantProfileRepository;
    private final TranslatorProfileRepository translatorProfileRepository;
    private final SmsServiceImpl smsService;
    private final EmailServiceImpl emailService;

    @Value("${interview.link}")
    private String INTERVIEW_LINK;

    @Transactional
    public User signUpAsUser(RegisterUserInput registerUserInput) throws ExceptionGraphql {
        if (registerUserInput.getPhone() == null || registerUserInput.getPhone().isBlank() ||
                registerUserInput.getPassword() == null || registerUserInput.getPassword().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = registerUserInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        if (userRepository.findByPhone(registerUserInput.getPhone()).isPresent()) {
            throw new ExceptionGraphql("Phone number is already used: " + registerUserInput.getPhone());
        }
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role newRole = Role.builder().name("USER").build();
                    return roleRepository.save(newRole);
                });
        User user = User.builder()
                .phone(phoneInput)
                .password(passwordEncoder.encode(registerUserInput.getPassword()))
                .image(null)
                .translatorProfile(null)
                .userProfile(new UserProfile())
                .roles(new ArrayList<>(List.of(userRole)))
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User signUpAsTranslator(RegisterTranslatorConsultantInput registerTranslatorConsultantInput) throws ExceptionGraphql {
        if (registerTranslatorConsultantInput.getPhone() == null || registerTranslatorConsultantInput.getPhone().isBlank() ||
                registerTranslatorConsultantInput.getPassword() == null || registerTranslatorConsultantInput.getPassword().isBlank() ||
                registerTranslatorConsultantInput.getEmail() == null || registerTranslatorConsultantInput.getEmail().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = registerTranslatorConsultantInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        if (userRepository.findByPhone(registerTranslatorConsultantInput.getPhone()).isPresent()) {
            throw new ExceptionGraphql("Phone number is already used: " + registerTranslatorConsultantInput.getPhone());
        }
        if (translatorProfileRepository.findByEmail(registerTranslatorConsultantInput.getEmail()).isPresent()
            || consultantProfileRepository.findByEmail(registerTranslatorConsultantInput.getEmail()).isPresent()) {
            throw new ExceptionGraphql("Email address is already used: " + registerTranslatorConsultantInput.getEmail());
        }
        Role translatorRole = roleRepository.findByName("TRANSLATOR")
                .orElseGet(() -> {
                    Role newRole = Role.builder().name("TRANSLATOR").build();
                    return roleRepository.save(newRole);
                });
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .email(registerTranslatorConsultantInput.getEmail())
                .languages(new ArrayList<>())
                .themes(new ArrayList<>())
                .build();
        User user = User.builder()
                .phone(phoneInput)
                .password(passwordEncoder.encode(registerTranslatorConsultantInput.getPassword()))
                .image(null)
                .userProfile(null)
                .translatorProfile(translatorProfile)
                .roles(new ArrayList<>(List.of(translatorRole)))
                .build();
        User savedUser = userRepository.save(user);
        String successRegistration = "Your registration as a Translator has partially completed. To finalize your registration, please attend an interview via the following link: " + INTERVIEW_LINK + ".";
        smsService.sendSms(savedUser.getPhone(), successRegistration);
        emailService.sendInvitationEmail(translatorProfile.getEmail(), "Invitation To Translator Interview", successRegistration);
        return savedUser;
    }

    @Transactional
    public User signUpAsConsultant(RegisterTranslatorConsultantInput registerTranslatorConsultantInput) throws ExceptionGraphql {
        if (registerTranslatorConsultantInput.getPhone() == null || registerTranslatorConsultantInput.getPhone().isBlank() ||
                registerTranslatorConsultantInput.getPassword() == null || registerTranslatorConsultantInput.getPassword().isBlank() ||
                registerTranslatorConsultantInput.getEmail() == null || registerTranslatorConsultantInput.getEmail().isBlank()) {
            throw new ExceptionGraphql("Empty values are not allowed");
        }
        String phoneInput = registerTranslatorConsultantInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new ExceptionGraphql("Phone number must contain at least one digit");
        }
        if (userRepository.findByPhone(registerTranslatorConsultantInput.getPhone()).isPresent()) {
            throw new ExceptionGraphql("Phone number is already used: " + registerTranslatorConsultantInput.getPhone());
        }
        if (translatorProfileRepository.findByEmail(registerTranslatorConsultantInput.getEmail()).isPresent()
                || consultantProfileRepository.findByEmail(registerTranslatorConsultantInput.getEmail()).isPresent()) {
            throw new ExceptionGraphql("Email address is already used: " + registerTranslatorConsultantInput.getEmail());
        }
        Role consultantRole = roleRepository.findByName("CONSULTANT")
                .orElseGet(() -> {
                    Role newRole = Role.builder().name("CONSULTANT").build();
                    return roleRepository.save(newRole);
                });
        ConsultantProfile consultantProfile = ConsultantProfile.builder()
                .email(registerTranslatorConsultantInput.getEmail())
                .languages(new ArrayList<>())
                .themes(new ArrayList<>())
                .build();
        User user = User.builder()
                .phone(phoneInput)
                .password(passwordEncoder.encode(registerTranslatorConsultantInput.getPassword()))
                .image(null)
                .userProfile(null)
                .consultantProfile(consultantProfile)
                .roles(new ArrayList<>(List.of(consultantRole)))
                .build();
        User savedUser = userRepository.save(user);
        String successRegistration = "Your registration as a Consultant has partially completed. To finalize your registration, please attend an interview via the following link: " + INTERVIEW_LINK + ".";
        smsService.sendSms(savedUser.getPhone(), successRegistration);
        emailService.sendInvitationEmail(consultantProfile.getEmail(), "Invitation To Consultant Interview", successRegistration);
        return savedUser;
    }

    @Transactional
    public User logIn(LoginUserInput loginUserInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserInput.getPhone(),
                        loginUserInput.getPassword()
                )
        );
        return userRepository.findByPhone(loginUserInput.getPhone())
                .orElseThrow();
    }
}
