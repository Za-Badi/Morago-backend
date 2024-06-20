package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositsRepository extends JpaRepository<Deposits, Long> {
    public List<Deposits> findByStatus(PaymentStatus status);


    List<Deposits> findByUserId(Long userId);
}