package com.habsida.morago.service;

public interface EmailService {

    void sendNotificationEmail(String to, String notificationSubject, String notificationBody);
    void sendInvitationEmail(String to, String invitationSubject, String invitationBody);
}
