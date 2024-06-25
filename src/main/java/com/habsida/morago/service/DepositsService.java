package com.habsida.morago.service;

import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;

import java.util.List;

public interface DepositsService {
    public List<DepositsDTO> getAllDeposits();
    public DepositsDTO getDepositById(Long id);
    public DepositsDTO addDeposit(CreateDepositsInput createDepositsInput);
    public DepositsDTO updateDeposit(Long id, UpdateDepositsInput updateDepositsInput);
    public void deleteDeposit(Long id);
    public List<DepositsDTO> getDepositsByStatus(PaymentStatus status);
    public List<DepositsDTO> getDepositByUserId(Long userId);
}
