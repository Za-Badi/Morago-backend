package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class NotificationMutationResolver implements GraphQLMutationResolver {

    private final NotificationService notificationService;

    public NotificationMutationResolver(NotificationService notificationService) {
        this.notificationService = notificationService;
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
}
