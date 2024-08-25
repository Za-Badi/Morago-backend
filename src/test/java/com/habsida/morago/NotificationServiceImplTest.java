package com.habsida.morago;


import com.habsida.morago.model.dto.NotificationDTO;
import com.habsida.morago.model.entity.Notification;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateNotificationInput;
import com.habsida.morago.model.inputs.UpdateNotificationInput;
import com.habsida.morago.repository.NotificationRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

//    @Test
//    void getAllNotification() {
//        Notification notification = new Notification();
//        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification));
//        when(modelMapper.map(any(Notification.class), eq(NotificationDTO.class))).thenReturn(new NotificationDTO());
//
//        List<NotificationDTO> result = notificationService.getAllNotification();
//
//        assertFalse(result.isEmpty());
//        verify(notificationRepository).findAll();
//        verify(modelMapper, times(1)).map(notification, NotificationDTO.class);
//    }

    @Test
    void getNotificationById() {
        Long id = 1L;
        Notification notification = new Notification();
        when(notificationRepository.findById(id)).thenReturn(Optional.of(notification));
        when(modelMapper.map(any(Notification.class), eq(NotificationDTO.class))).thenReturn(new NotificationDTO());

        NotificationDTO result = notificationService.getNotificationById(id);

        assertNotNull(result);
        verify(notificationRepository).findById(id);
        verify(modelMapper).map(notification, NotificationDTO.class);
    }

    @Test
    void addNotificationWithDetailedInput() {
        CreateNotificationInput input = new CreateNotificationInput();
        input.setUserId(1L);
        input.setTitle("Test Title");
        input.setText("Test Text");

        User user = new User();
        user.setId(input.getUserId());

        Notification mappedNotification = new Notification();
        mappedNotification.setUser(user);
        mappedNotification.setTitle(input.getTitle());
        mappedNotification.setText(input.getText());
        mappedNotification.setTime(LocalTime.now());
        mappedNotification.setDate(LocalDate.now());

        Notification savedNotification = new Notification();

        when(userRepository.findById(input.getUserId())).thenReturn(Optional.of(user));
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);
        when(modelMapper.map(savedNotification, NotificationDTO.class)).thenReturn(new NotificationDTO());

        NotificationDTO result = notificationService.addNotification(input);

        assertNotNull(result);
        verify(userRepository).findById(input.getUserId());
        verify(notificationRepository).save(any(Notification.class));
        verify(modelMapper).map(savedNotification, NotificationDTO.class);
    }

    @Test
    void updateNotificationWithDetailedInput() {
        Long id = 1L;
        UpdateNotificationInput input = new UpdateNotificationInput();
        input.setTitle("Updated Title");
        input.setText("Updated Text");

        Notification existingNotification = new Notification();
        existingNotification.setId(id);
        existingNotification.setTitle("Old Title");
        existingNotification.setText("Old Text");

        when(notificationRepository.findById(id)).thenReturn(Optional.of(existingNotification));

        doAnswer(invocation -> {
            UpdateNotificationInput src = invocation.getArgument(0);
            Notification dest = invocation.getArgument(1);
            dest.setTitle(src.getTitle());
            dest.setText(src.getText());
            dest.setTime(LocalTime.now());
            dest.setDate(LocalDate.now());
            return null;
        }).when(modelMapper).map(any(UpdateNotificationInput.class), any(Notification.class));

        when(notificationRepository.save(any(Notification.class))).thenReturn(existingNotification);

        notificationService.updateNotification(id, input);

        assertEquals("Updated Title", existingNotification.getTitle());
        assertEquals("Updated Text", existingNotification.getText());
        verify(notificationRepository).save(existingNotification);
    }

    @Test
    void deleteNotification() {
        Long notificationId = 1L;
        Notification notification = new Notification();
        notification.setId(notificationId);

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        doNothing().when(notificationRepository).deleteById(notificationId);

        notificationService.deleteNotification(notificationId);

        verify(notificationRepository).findById(notificationId);
        verify(notificationRepository).deleteById(notificationId);
    }

    @Test
    void getNotificationByUserId() {
        Long userId = 1L;
        Notification notification = new Notification();
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(notificationRepository.findUserById(userId)).thenReturn(Arrays.asList(notification));
        when(modelMapper.map(any(Notification.class), eq(NotificationDTO.class))).thenReturn(new NotificationDTO());

        List<NotificationDTO> result = notificationService.getNotificationByUserId(userId);

        assertFalse(result.isEmpty());
        verify(userRepository).findById(userId);
        verify(notificationRepository).findUserById(userId);
        verify(modelMapper).map(notification, NotificationDTO.class);
    }
}
