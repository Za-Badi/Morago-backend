package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationMutationResolver implements GraphQLMutationResolver {

    private NotificationService notificationService;

    public NotificationMutationResolver(NotificationService notificationService) {
        this.notificationService = notificationService;
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
}