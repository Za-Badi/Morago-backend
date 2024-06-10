package com.habsida.morago.service;

import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.inputs.WithdrawalInput;

import java.util.List;
import java.util.Optional;

public interface WithdrawalService {

    public List<Withdrawals> getAllWithdrawals();

    public Withdrawals getWithdrawalById(Long id) throws Exception;

    public Withdrawals addWithdrawal(WithdrawalInput withdrawalDto);

    public Withdrawals updateWithdrawal(Long id, WithdrawalInput withdrawalDto) throws Exception;

    public void deleteWithdrawal(Long id) throws Exception;
}