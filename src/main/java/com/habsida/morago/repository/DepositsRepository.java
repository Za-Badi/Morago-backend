package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepositsRepository extends JpaRepository<Deposits, Long> {
    @Query("SELECT d FROM Deposits d JOIN FETCH d.user")
    List<Deposits> findAllWithUsers();

    @Query("SELECT d FROM Deposits d JOIN FETCH d.user WHERE d.id = :id")
    Optional<Deposits> findByIdWithUser(@Param("id") Long id);

    @Query("SELECT d FROM Deposits d JOIN FETCH d.user WHERE d.status = :status")
    List<Deposits> findByStatusWithUser(@Param("status") PaymentStatus status);

    @Query("SELECT d FROM Deposits d JOIN FETCH d.user WHERE d.user.id = :userId")
    List<Deposits> findByUserIdWithUser(@Param("userId") Long userId);
}