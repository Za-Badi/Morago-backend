//package com.habsida.morago.controllers;
//
//import com.habsida.morago.model.inputs.NotificationInput;
//import com.habsida.morago.model.entity.Notification;
//import com.habsida.morago.resolver.NotificationQueryResolver;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//public class NotificationController {
//    private NotificationQueryResolver notificationResolver;
//
//    public NotificationController(NotificationQueryResolver notificationResolver) {
//        this.notificationResolver = notificationResolver;
//    }
//
//    @QueryMapping
//    public List<Notification> getAllNotification() {
//        return notificationResolver.getAllNotification();
//    }
//
//    @QueryMapping
//    public Notification getNotificationById(@Argument Long id) throws Exception {
//        return notificationResolver.getNotificationById(id);
//    }
//
//    @MutationMapping
//    public Notification addNotification(@Argument NotificationInput notificationDto) {
//        return notificationResolver.addNotification(notificationDto);
//    }
//
//    @MutationMapping
//    public Notification updateNotification(@Argument Long id, @Argument NotificationInput notificationDto) throws Exception {
//        return notificationResolver.updateNotification(id, notificationDto);
//    }
//
//    @MutationMapping
//    public Boolean deleteNotification(@Argument Long id) throws Exception {
//        return notificationResolver.deleteNotification(id);
//    }
//
//}