package com.habsida.morago.serviceImpl;

import com.habsida.morago.service.EmailService;
import com.habsida.morago.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public void sendNotificationEmail(String to, String subject, String notificationArgs) {
        String notificationTemplate = "You have a new notification:\n%s\n";

        String text = String.format(notificationTemplate, notificationArgs);
        emailUtil.sendSimpleEmail(to, subject, text);
    }

    @Override
    public void sendInvitationEmail(String to, String subject, String invitationArgs) {
        String invitationTemplate = "You have a new invitation:\n%s\n";

        String text = String.format(invitationTemplate, invitationArgs);
        emailUtil.sendSimpleEmail(to, subject, text);
    }
}
