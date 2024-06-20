package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.repository.NotificationRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    private final FirebaseService firebaseService;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository,
                                   FirebaseService firebaseService) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.firebaseService = firebaseService;

    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));
    }

    @Override
    public Notification addNotification(CreateNotificationInput createNotificationInput) {
        User user = userRepository.findById(createNotificationInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found"));

        Notification notification = new Notification();
        notification.setTitle(createNotificationInput.getTitle());
        notification.setText(createNotificationInput.getText());

        notification.setTime(LocalTime.now());
        notification.setDate(LocalDate.now());

        notification.setUser(user);
        notificationRepository.save(notification);

        String fcmToken = user.getFcmToken();
        if(fcmToken != null && !fcmToken.isEmpty()) {
            firebaseService.sendNotification(fcmToken, notification.getTitle(), notification.getText());
        }else {
            System.out.println("User doesn't have a valid FCM token");
        }

        return notification;
    }

    @Override
    public Notification updateNotification(Long id, UpdateNotificationInput updateNotificationInput) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for this id: " + id));

        if (updateNotificationInput.getText() != null && !updateNotificationInput.getText().isEmpty()) {
            notification.setText(updateNotificationInput.getText());
        }
        if (updateNotificationInput.getTitle() != null && !updateNotificationInput.getTitle().isEmpty()) {
            notification.setTitle(updateNotificationInput.getTitle());
        }

        if (LocalTime.now() != null) {
            notification.setTime(LocalTime.now());
        }
        if (LocalDate.now() != null) {
            notification.setDate(LocalDate.now());
        }

        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));
        notificationRepository.deleteById(id);

    }

    @Override
    public List<Notification> getNotificationByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Notification> notificationByUserId = notificationRepository.findUserById(userId);
        return notificationByUserId;
    }


}