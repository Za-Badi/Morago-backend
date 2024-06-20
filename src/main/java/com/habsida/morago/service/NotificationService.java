package com.habsida.morago.service;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;

import java.util.List;

public interface NotificationService {

    public List<Notification> getAllNotification();

    public Notification getNotificationById(Long id);

    public Notification addNotification(CreateNotificationInput createNotificationInput);

    public Notification updateNotification(Long id, UpdateNotificationInput updateNotificationInput);

    public void deleteNotification(Long id);

    public List<Notification> getNotificationByUserId(Long userId);

}