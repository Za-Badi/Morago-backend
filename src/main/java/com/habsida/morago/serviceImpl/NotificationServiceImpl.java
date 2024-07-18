package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.repository.NotificationRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import com.habsida.morago.service.NotificationService;
import com.habsida.morago.service.SmsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SmsService smsService;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository,
                                   ModelMapper modelMapper, SmsService smsService) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.smsService = smsService;
    }

    @Transactional(readOnly = true)
    public List<NotificationDTO> getAllNotification() {
        return notificationRepository.findAll()
                .stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NotificationDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));
        return modelMapper.map(notification, NotificationDTO.class);
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Notification not found for id: " + id));
        notificationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<NotificationDTO> getNotificationByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Notification> notifications = notificationRepository.findUserById(userId);
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void notifyCallCreation(User translator, User user) {
        // Create notification for the translator
        CreateNotificationInput translatorNotificationInput = new CreateNotificationInput();
        translatorNotificationInput.setUserId(translator.getId());
        translatorNotificationInput.setTitle("New Call Request");
        String translatorNotificationText = "You have a new call request from " + user.getFirstName();
        translatorNotificationInput.setText(translatorNotificationText);
        addNotification(translatorNotificationInput);
        smsService.sendSms(translator.getPhone(), translatorNotificationText);


        // Create notification for the user
        CreateNotificationInput userNotificationInput = new CreateNotificationInput();
        userNotificationInput.setUserId(user.getId());
        userNotificationInput.setTitle("Call Request Sent");
        String userNotificationText = "Your call request has been sent to " + translator.getFirstName();
        userNotificationInput.setText(userNotificationText);
        addNotification(userNotificationInput);
        smsService.sendSms(user.getPhone(), userNotificationText);
    }

    @Override
    @Transactional
    public void notifyCallEnd(User caller, User translator, Call call) {
        // Create notification for the caller
        CreateNotificationInput callerNotificationInput = new CreateNotificationInput();
        callerNotificationInput.setUserId(caller.getId());
        callerNotificationInput.setTitle("Call Ended");
        String userNotificationText = "Your call with " + translator.getFirstName() + " has ended. Duration: " + call.getDuration() + " minutes.";
        callerNotificationInput.setText(userNotificationText);
        addNotification(callerNotificationInput);
        smsService.sendSms(caller.getPhone(), userNotificationText);

        // Create notification for the translator
        CreateNotificationInput translatorNotificationInput = new CreateNotificationInput();
        translatorNotificationInput.setUserId(translator.getId());
        translatorNotificationInput.setTitle("Call Ended");
        String translatorNotificationText = "Your call with " + caller.getFirstName() + " has ended. Duration: " + call.getDuration() + " minutes.";
        translatorNotificationInput.setText(translatorNotificationText);
        addNotification(translatorNotificationInput);
        smsService.sendSms(translator.getPhone(), translatorNotificationText);
    }
}
