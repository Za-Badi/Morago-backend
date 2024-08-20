package com.habsida.morago.test;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.enums.UserStatus;
import com.habsida.morago.service.EmailService;
import com.habsida.morago.serviceImpl.EmailServiceImpl;
import com.habsida.morago.util.EmailUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailServiceImpl emailService;
    @Mock
    private EmailUtil emailUtil;
    @Mock
    JavaMailSender javaMailSender;

    private String toEmail;
    private String testSubject;
    private String testBody;

    @BeforeEach
    public void setUp() {
        toEmail = "test@gmail.com";
        testSubject = "Test Subject";
        testBody = "Test Body";
    }

    @Test
    public void testSendNotificationEmail() {
        doNothing().when(emailUtil).sendSimpleEmail(any(), any(), any());
//        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailService.sendNotificationEmail(toEmail, testSubject, testBody);
        verify(emailUtil, times(1)).sendSimpleEmail(any(), any(), (String) any());
//        verify(javaMailSender, times(1)).send((SimpleMailMessage) any());

    }


    @Test
    public void testSendInvitationEmail() {
        doNothing().when(emailUtil).sendSimpleEmail(any(), any(), any());
        emailService.sendInvitationEmail(toEmail, testSubject, testBody);
        verify(emailUtil, times(1)).sendSimpleEmail(any(), any(), any());
    }
}
