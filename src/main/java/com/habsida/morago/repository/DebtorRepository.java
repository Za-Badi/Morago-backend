package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtorRepository extends JpaRepository<Debtor, Long> {
    List<Debtor> findByUserId(Long userId);
}
