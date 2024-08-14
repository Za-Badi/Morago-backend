package com.habsida.morago.serviceImpl;

import com.habsida.morago.service.EmailService;
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
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);
    }

    public void sendNotificationMessage(String to, String subject, String notificationArgs) {
        String notificationTemplate = "You have a new notification:\n%s\n";

        String text = String.format(notificationTemplate, notificationArgs);
        sendSimpleMessage(to, subject, text);
    }

    public void sendInvitationMessage(String to, String subject, String invitationArgs) {
        String invitationTemplate = "You have a new invitation:\n%s\n";

        String text = String.format(invitationTemplate, invitationArgs);
        sendSimpleMessage(to, subject, text);
    }
}
