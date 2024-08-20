package com.habsida.morago.service;

public interface EmailService {

    void sendNotificationEmail(String to, String subject, String notificationArgs);
    void sendInvitationEmail(String to, String subject, String invitationArgs);
}
