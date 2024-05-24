package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Withdrawals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawals, Long> {
}
