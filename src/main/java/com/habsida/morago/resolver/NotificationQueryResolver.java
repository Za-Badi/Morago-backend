package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationQueryResolver implements GraphQLQueryResolver {

    private NotificationService notificationService;

    public NotificationQueryResolver(NotificationService notificationService) {
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

}