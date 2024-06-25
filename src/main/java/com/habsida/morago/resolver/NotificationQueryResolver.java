package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationQueryResolver implements GraphQLQueryResolver {

    private final NotificationService notificationService;

    public NotificationQueryResolver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<NotificationDTO> getAllNotification() {
        return notificationService.getAllNotification();
    }

    public NotificationDTO getNotificationById(Long id) {
        return notificationService.getNotificationById(id);
    }

    public List<NotificationDTO> getNotificationsByUserId(Long userId) {
        return notificationService.getNotificationByUserId(userId);
    }
}
