package com.habsida.morago.service;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.inputs.NotificationInput;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    public List<Notification> getAllNotification();
    public Notification getNotificationById(Long id) throws Exception;
    public Notification addNotification(NotificationInput notificationDto);

    public Notification updateNotification(Long id, NotificationInput notificationDto) throws Exception;

    public void deleteNotification(Long id) throws Exception;

}