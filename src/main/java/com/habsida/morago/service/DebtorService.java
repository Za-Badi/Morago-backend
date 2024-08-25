package com.habsida.morago.service;

import com.habsida.morago.model.dto.DebtorDTO;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.model.results.PageOutput;

import java.util.List;

public interface DebtorService {
    public PageOutput<DebtorDTO> getAllDebtors(PagingInput pagingInput);
    public DebtorDTO getDebtorById(Long id);
    public DebtorDTO addDebtor(CreateDebtorInput createDebtorInput);
    public DebtorDTO updateDebtor(Long id, UpdateDebtorInput updateDebtorInput);
    public void deleteDebtor(Long id);
    public List<DebtorDTO> getDebtorByUserId(Long userId);
}
