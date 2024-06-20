package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebtorResolver {

    private final DebtorService debtorService;

    @Autowired
    public DebtorResolver(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    public List<Debtor> getAllDebtors() {
        return debtorService.getAllDebtors();
    }

    public Debtor getDebtorById(Long id) {
        return debtorService.getDebtorById(id);
    }

    public List<Debtor> getDebtorByUserId(Long userId) {
        return debtorService.getDebtorByUserId(userId);
    }

}