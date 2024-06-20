package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationResolver {

    private NotificationService notificationService;

    public NotificationResolver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<Notification> getAllNotification() {
        return notificationService.getAllNotification();
    }

    public Notification getNotificationById(Long id) {
        return notificationService.getNotificationById(id);
    }

    public List<Notification> getNotificationsByUserId(Long userId){
        return notificationService.getNotificationByUserId(userId);
    }

    public Notification addNotification(CreateNotificationInput createNotificationInput) {
        return notificationService.addNotification(createNotificationInput);
    }

    public Notification updateNotification(Long id, UpdateNotificationInput updateNotificationInput) {
        return notificationService.updateNotification(id, updateNotificationInput);
    }

    public Boolean deleteNotification(Long id) {
        notificationService.deleteNotification(id);
        return true;
    }

    public List<Notification> getNotificationByUserId(Long userId){
        return notificationService.getNotificationByUserId(userId);
    }
}