package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.results.PageOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawals, Long> {
    Page<Withdrawals> findByUserId(Long userId, Pageable pageable);

    Page<Withdrawals> findByStatus(PaymentStatus status, Pageable pageable);
}
