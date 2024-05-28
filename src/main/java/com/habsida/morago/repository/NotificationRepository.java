package com.habsida.morago.repository;
import com.habsida.morago.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findUserById(Long userId);
}
