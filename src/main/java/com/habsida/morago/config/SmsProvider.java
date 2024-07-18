package com.habsida.morago.config;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.model.StorageType;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SmsProvider {

    private final DefaultMessageService messageService;
    @Value("${sms.from-number}") String FROM;


    public SmsProvider(@Value("${sms.api-key}") String API_KEY,
                       @Value("${sms.api-secret-key}") String API_SECRET_KEY,
                       @Value("${sms.domain}") String DOMAIN) {
        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, DOMAIN);
    }

    public boolean sendSms(String to, String notificationText) {
        Message message = new Message();
        message.setFrom(FROM);
        message.setTo(to);
        message.setText(notificationText);

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
        String statusCode = response.getStatusCode();
        boolean result = statusCode.equals("2000");
        return result;
    }

    public boolean sendMms(String to, String notificationText, File notificationFile) {
        Message message = new Message();
        message.setFrom(FROM);
        message.setTo(to);
        message.setText(notificationText);

        String imageId = messageService.uploadFile(notificationFile, StorageType.MMS, null);
        message.setImageId(imageId);

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
        String statusCode = response.getStatusCode();
        boolean result = statusCode.equals("2000");
        return result;
    }
}
