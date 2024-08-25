package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepositsRepository extends JpaRepository<Deposits, Long> {
    @Query("SELECT d FROM Deposits d JOIN FETCH d.user")
    List<Deposits> findAllWithUsers(Pageable pageable);

    @Query("SELECT d FROM Deposits d JOIN FETCH d.user WHERE d.id = :id")
    Optional<Deposits> findByIdWithUser(@Param("id") Long id);

    @Query(value = "SELECT d, u FROM Deposits d JOIN FETCH d.user u WHERE d.status = :status",
        countQuery = "SELECT count(d.id) FROM Deposits d JOIN d.user WHERE d.status = :status")
    Page<Deposits> findByStatusWithUser(@Param("status") PaymentStatus status, Pageable pageable);

    @Query(value = "SELECT d, u FROM Deposits d JOIN FETCH d.user u WHERE d.user.id = :userId",
        countQuery = "SELECT count(d.id) FROM Deposits d JOIN d.user WHERE d.user.id = :userId")
    Page<Deposits> findByUserIdWithUser(@Param("userId") Long userId, Pageable pageable);
}