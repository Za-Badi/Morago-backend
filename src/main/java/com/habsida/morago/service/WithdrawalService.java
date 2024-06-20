package com.habsida.morago.service;

import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;

import java.util.List;

public interface WithdrawalService {

    public List<Withdrawals> getAllWithdrawals();

    public Withdrawals getWithdrawalById(Long id) ;

    public Withdrawals addWithdrawal(CreateWithdrawalInput createWithdrawalInput);

    public Withdrawals updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput);

    public void deleteWithdrawal(Long id);

    public List<Withdrawals> getWithdrawalsByStatus (PaymentStatus status);
    public List<Withdrawals> getWithdrawalsByUserId (Long userId);


}