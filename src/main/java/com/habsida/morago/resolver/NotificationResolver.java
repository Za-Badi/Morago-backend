package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.inputs.NotificationInput;
import com.habsida.morago.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



@Component
public class NotificationResolver {

    private NotificationService notificationService;

    public NotificationResolver(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public List<Notification> getAllNotification(){
        return notificationService.getAllNotification();
    }

    public Notification getNotificationById(Long id) throws Exception {
        return notificationService.getNotificationById(id);
    }

    public Notification addNotification(NotificationInput notificationDto){
        return notificationService.addNotification(notificationDto);
    }

    public Notification updateNotification(Long id, NotificationInput notificationDto) throws Exception {
        return notificationService.updateNotification(id, notificationDto);
    }

    public Boolean deleteNotification(Long id) throws Exception {
        notificationService.deleteNotification(id);
        return true;
    }
}