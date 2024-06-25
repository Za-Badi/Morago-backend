package com.habsida.morago.service;

import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;

import java.util.List;

public interface NotificationService {

    public List<NotificationDTO> getAllNotification();

    public NotificationDTO getNotificationById(Long id);

    public NotificationDTO addNotification(CreateNotificationInput createNotificationInput);

    public NotificationDTO updateNotification(Long id, UpdateNotificationInput updateNotificationInput);

    public void deleteNotification(Long id);

    public List<NotificationDTO> getNotificationByUserId(Long userId);

}
