package com.habsida.morago.service;
import com.habsida.morago.model.inputs.DebtorInput;
import com.habsida.morago.model.entity.Debtor;

import java.util.List;

public interface DebtorService {
    public List<Debtor> getAllDebtors();

    public Debtor getDebtorById(Long id) throws Exception;

    public Debtor addDebtor(DebtorInput debtorInput);

    public Debtor updateDebtor(Long id, DebtorInput debtorInput) throws Exception;

    public void deleteDebtor(Long id) throws Exception;
}