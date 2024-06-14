package com.habsida.morago.service;

import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.inputs.DepositsInput;

import java.util.List;

import java.util.List;

public interface DepositsService {
    public List<Deposits> getAllDeposits();

    public Deposits getDepositById(Long id) throws Exception;

    public Deposits addDeposit(DepositsInput depositsInput) throws Exception;

    public Deposits updateDeposit(Long id, DepositsInput depositsInput) throws Exception;

    public void deleteDeposit(Long id) throws Exception;

    public List<Deposits> getDepositsByStatus(CallStatus status);

    public List<Deposits> getDepositByUserId(Long userId);
}