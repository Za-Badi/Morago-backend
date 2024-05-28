package com.habsida.morago.service;

import com.habsida.morago.model.entity.Deposits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface DepositsService {

    List<Deposits> getAllDeposits();
    Optional<Deposits> getDepositById(Long id);

    List<Deposits> getDepositsByUserId(Long userId);

    Deposits saveDeposit(Deposits deposits);

    Deposits updateDeposits(Long id, Deposits deposits);

    void deleteDeposits(Long id);

}
