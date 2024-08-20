package com.habsida.morago.service;

import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.model.results.PageOutput;

import java.util.List;

public interface WithdrawalService {

    public PageOutput<WithdrawalsDTO> getAllWithdrawals(PagingInput pagingInput);

    public WithdrawalsDTO getWithdrawalById(Long id);

    public WithdrawalsDTO addWithdrawal(CreateWithdrawalInput createWithdrawalInput);

    public WithdrawalsDTO updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput);

    public void deleteWithdrawal(Long id);

    public PageOutput<WithdrawalsDTO> getWithdrawalsByStatus(PaymentStatus status, PagingInput pagingInput);

    public PageOutput<WithdrawalsDTO> getWithdrawalsByUserId(Long userId, PagingInput pagingInput);
}
