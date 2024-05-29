package com.habsida.morago.service;

import com.habsida.morago.model.entity.Withdrawals;

import java.util.List;
import java.util.Optional;

public interface WithdrawalService {
    List<Withdrawals> getAllWithdrawals();
    Optional<Withdrawals> getWithdrawalsById(Long id);

    List<Withdrawals> getWithdrawalsByUserId(Long userId);

    Withdrawals saveWithdrawals(Withdrawals withdrawals);

    Withdrawals updateWithdrawals(Long id, Withdrawals withdrawals);

    void deleteWithdrawals(Long id);
}