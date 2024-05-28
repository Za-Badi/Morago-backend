package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositsRepository extends JpaRepository<Deposits, Long> {
    List<Deposits> findByUserId(Long userId);
}
