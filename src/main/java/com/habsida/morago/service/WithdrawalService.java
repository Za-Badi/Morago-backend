package com.habsida.morago.service;

import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;

import java.util.List;

public interface WithdrawalService {

    public List<WithdrawalsDTO> getAllWithdrawals();

    public WithdrawalsDTO getWithdrawalById(Long id);

    public WithdrawalsDTO addWithdrawal(CreateWithdrawalInput createWithdrawalInput);

    public WithdrawalsDTO updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput);

    public void deleteWithdrawal(Long id);

    public List<WithdrawalsDTO> getWithdrawalsByStatus(PaymentStatus status);

    public List<WithdrawalsDTO> getWithdrawalsByUserId(Long userId);
}
