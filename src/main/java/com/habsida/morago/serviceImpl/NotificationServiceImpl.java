package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.NotificationInput;
import com.habsida.morago.repository.NotificationRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) throws Exception {
        return notificationRepository.findById(id).orElseThrow(() -> new Exception("Notification not found for id: " + id));
    }

    @Override
    public Notification addNotification(NotificationInput notificationDto) {
        User user = userRepository.findById(notificationDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Notification notification = new Notification();
        notification.setTitle(notificationDto.getTitle());
        notification.setText(notificationDto.getText());
        notification.setUser(user);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, NotificationInput notificationDto) throws Exception {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new Exception("Notification not found for this id: " + id));

        if (notificationDto.getText() != null) {
            notification.setText(notificationDto.getText());
        }
        if (notificationDto.getTitle() != null) {
            notification.setTitle(notificationDto.getTitle());
        }

        if (notificationDto.getUserId() != null) {
            User user = userRepository.findById(notificationDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            notification.setUser(user);
        }

        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long id) throws Exception {
        notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found for id: " + id));
        notificationRepository.deleteById(id);

    }
}