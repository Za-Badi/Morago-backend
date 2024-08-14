package com.habsida.morago.serviceImpl;

import com.habsida.morago.util.SmsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl {

    private final SmsProvider smsProvider;

    public boolean sendSms(String to, String notificationText) {
        try {
            return smsProvider.sendSms(to, notificationText);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMms(String to, String notificationText, File notficationFile) {
        try {
            return smsProvider.sendMms(to, notificationText, notficationFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
