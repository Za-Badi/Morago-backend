package com.habsida.morago.serviceImpl;

import com.habsida.morago.config.SmsProvider;
import com.habsida.morago.service.SmsService;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsProvider smsProvider;

    @Override
    public boolean sendSms(String to, String notificationText) {
        try {
            return smsProvider.sendSms(to, notificationText);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendMms(String to, String notificationText, File notficationFile) {
        try {
            return smsProvider.sendMms(to, notificationText, notficationFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
