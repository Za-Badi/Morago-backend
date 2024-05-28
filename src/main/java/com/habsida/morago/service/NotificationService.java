package com.habsida.morago.service;

import com.habsida.morago.model.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Optional<Notification> getNotificationById(Long id);
    List<Notification> getNotificationsByUserId(Long userId);
    Notification saveNotification(Notification notification);
    Notification updateNotification(Long id, Notification notification);
    void deleteNotification(Long id);
}
