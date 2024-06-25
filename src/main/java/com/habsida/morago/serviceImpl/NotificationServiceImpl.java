package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.repository.NotificationRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository,
                                   ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NotificationDTO> getAllNotification() {
        return notificationRepository.findAll()
                .stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));
        return modelMapper.map(notification, NotificationDTO.class);
    }

    @Override
    public NotificationDTO addNotification(CreateNotificationInput createNotificationInput) {
        User user = userRepository.findById(createNotificationInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createNotificationInput.getUserId()));

        Notification notification = new Notification();
        notification.setTitle(createNotificationInput.getTitle());
        notification.setText(createNotificationInput.getText());
        notification.setTime(LocalTime.now());
        notification.setDate(LocalDate.now());
        notification.setUser(user);

        Notification savedNotification = notificationRepository.save(notification);
        return modelMapper.map(savedNotification, NotificationDTO.class);
    }

    @Override
    public NotificationDTO updateNotification(Long id, UpdateNotificationInput updateNotificationInput) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));

        if (updateNotificationInput.getText() != null && !updateNotificationInput.getText().isEmpty()) {
            notification.setText(updateNotificationInput.getText());
        }
        if (updateNotificationInput.getTitle() != null && !updateNotificationInput.getTitle().isEmpty()) {
            notification.setTitle(updateNotificationInput.getTitle());
        }
        notification.setTime(LocalTime.now());
        notification.setDate(LocalDate.now());

        Notification updatedNotification = notificationRepository.save(notification);
        return modelMapper.map(updatedNotification, NotificationDTO.class);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationDTO> getNotificationByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Notification> notifications = notificationRepository.findUserById(userId);
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }
}
