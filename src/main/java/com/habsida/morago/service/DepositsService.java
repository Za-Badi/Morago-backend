package com.habsida.morago.services;


import com.habsida.morago.model.inputs.DepositsInput;
import com.habsida.morago.model.entity.Deposits;

import java.util.List;

public interface DepositsService {
    public List<Deposits> getAllDeposits();
    public Deposits getDepositById(Long id) throws Exception;
    public Deposits addDeposit(DepositsInput depositDto);
    public Deposits updateDeposit(Long id, DepositsInput depositDto) throws Exception;
    public void deleteDeposit(Long id) throws Exception;
}