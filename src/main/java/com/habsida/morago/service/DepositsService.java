package com.habsida.morago.service;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;

import java.util.List;

public interface DepositsService {
    public List<Deposits> getAllDeposits();

    public Deposits getDepositById(Long id);

    public Deposits addDeposit(CreateDepositsInput createDepositsInput);

    public Deposits updateDeposit(Long id, UpdateDepositsInput updateDepositsInput);

    public void deleteDeposit(Long id);

    public List<Deposits> getDepositsByStatus(PaymentStatus status);

    List<Deposits> getDepositByUserId(Long userId);

//    public List<Deposits> getDepositByUserId(User userId);

}