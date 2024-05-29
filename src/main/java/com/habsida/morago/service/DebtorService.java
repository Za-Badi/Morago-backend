package com.habsida.morago.service;

import com.habsida.morago.model.entity.Debtor;

import java.util.List;
import java.util.Optional;

public interface DebtorService {

    List<Debtor> getAllDebtors();
    Optional<Debtor> getDebtorById(Long id);

    List<Debtor> getDebtorsByUserId(Long userId);

    Debtor saveDebtor(Debtor debtor);

    Debtor updateDebtor(Long id, Debtor debtor);
    void deleteDebtor(Long id);
}