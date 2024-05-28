package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Withdrawals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawals, Long> {
    List<Withdrawals> findByUserId(Long userId);
}
