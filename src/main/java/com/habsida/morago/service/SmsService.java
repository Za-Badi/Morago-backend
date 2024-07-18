package com.habsida.morago.service;

import java.io.File;

public interface SmsService {

    boolean sendSms(String to, String notificationText);

    boolean sendMms(String to, String notificationText, File notficationFile);
}
