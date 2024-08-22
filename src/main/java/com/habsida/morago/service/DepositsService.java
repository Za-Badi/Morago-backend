package com.habsida.morago.service;

import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.model.results.PageOutput;

import java.util.List;

public interface DepositsService {
    public DepositsDTO getDepositById(Long id);
    public DepositsDTO addDeposit(CreateDepositsInput createDepositsInput);
    public DepositsDTO updateDeposit(Long id, UpdateDepositsInput updateDepositsInput);
    public void deleteDeposit(Long id);
    public PageOutput<DepositsDTO> getDepositsByStatus(PaymentStatus status, PagingInput pagingInput);
    public PageOutput<DepositsDTO> getDepositByUserId(Long userId, PagingInput pagingInput);
}
