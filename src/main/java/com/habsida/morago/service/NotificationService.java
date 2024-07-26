package com.habsida.morago.service;

import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.User;
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

    // New method to notify users about the creation of a call
    public void notifyCallCreation(User translator, User user);

    // New method to notify users about the end of a call
    public void notifyCallEnd(User caller, User translator, Call call);

    public void notifyConsultantCallCreation(User translator, User user, User consultant);

    public void notifyConsultantCallEnd(User caller, User translator, User consultant, Call call);

}
