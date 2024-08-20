package com.habsida.morago.serviceImpl;

import com.habsida.morago.service.EmailService;
import com.habsida.morago.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailUtil emailUtil;

    @Override
    public void sendNotificationEmail(String to, String notificationSubject, String notificationBody) {
        String notificationTemplate = "You have a new notification:\n%s\n";
        String text = String.format(notificationTemplate, notificationBody);
        emailUtil.sendSimpleEmail(to, notificationSubject, text);
    }

    @Override
    public void sendInvitationEmail(String to, String invitationSubject, String invitationBody) {
        String invitationTemplate = "You have a new invitation:\n%s\n";
        String text = String.format(invitationTemplate, invitationBody);
        emailUtil.sendSimpleEmail(to, invitationSubject, text);
    }
}
