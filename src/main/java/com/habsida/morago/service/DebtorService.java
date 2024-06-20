package com.habsida.morago.service;

import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;

import java.util.List;

public interface DebtorService {
    public List<Debtor> getAllDebtors();

    public Debtor getDebtorById(Long id);

    public Debtor addDebtor(CreateDebtorInput createDebtorInput);

    public Debtor updateDebtor(Long id, UpdateDebtorInput updateDepositsInput);

    public void deleteDebtor(Long id);
     public List<Debtor> getDebtorByUserId(Long userId);
}