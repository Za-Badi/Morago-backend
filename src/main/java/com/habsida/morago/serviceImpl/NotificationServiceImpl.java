package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.repository.NotificationRepository;
import com.habsida.morago.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findUserById(userId);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);
        if (existingNotification.isPresent()) {
            Notification updatedNotification = existingNotification.get();
            updatedNotification.setTitle(notification.getTitle());
            updatedNotification.setText(notification.getText());
            updatedNotification.setDate(notification.getDate());
            updatedNotification.setTime(notification.getTime());
            return notificationRepository.save(updatedNotification);
        } else {
            return null;
        }
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
