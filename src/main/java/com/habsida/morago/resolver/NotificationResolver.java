package com.habsida.morago.resolver;

import com.habsida.morago.model.dto.NotificationDTO;
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

    public List<NotificationDTO> getAllNotification() {
        return notificationService.getAllNotification();
    }

    public NotificationDTO getNotificationById(Long id) {
        return notificationService.getNotificationById(id);
    }

    public List<NotificationDTO> getNotificationsByUserId(Long userId){
        return notificationService.getNotificationByUserId(userId);
    }

    public NotificationDTO addNotification(CreateNotificationInput createNotificationInput) {
        return notificationService.addNotification(createNotificationInput);
    }

    public NotificationDTO updateNotification(Long id, UpdateNotificationInput updateNotificationInput) {
        return notificationService.updateNotification(id, updateNotificationInput);
    }

    public Boolean deleteNotification(Long id) {
        notificationService.deleteNotification(id);
        return true;
    }

    public List<NotificationDTO> getNotificationByUserId(Long userId){
        return notificationService.getNotificationByUserId(userId);
    }
}