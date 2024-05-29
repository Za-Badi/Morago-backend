package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotificationResolver {
    private final NotificationService notificationService;

    @Autowired
    public NotificationResolver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

//    public List<Notification> getAllNotifications() {
//        return notificationService.getAllNotification();
//    }

    public Optional<Notification> getNotificationById(Long id) throws Exception {
        return notificationService.getNotificationById(id);
    }

//    public Notification addNotification(Notification notification) {
//        return notificationService.addNotification(notification);
//    }

    public Notification updateNotification(Long id, Notification notificationUpdate) throws Exception {
        return notificationService.updateNotification(id, notificationUpdate);
    }

    public Boolean deleteNotification(Long id) throws Exception {
        notificationService.deleteNotification(id);
        return true;
    }
}
