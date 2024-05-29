package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DebtorResolver  {
    private final DebtorService debtorService;

    @Autowired
    public DebtorResolver(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    public List<Debtor> getAllDebtors() {
        return debtorService.getAllDebtors();
    }

    public Optional<Debtor> getDebtorById(Long id) throws Exception {
        return debtorService.getDebtorById(id);
    }

//    public Debtor addDebtor(Debtor debtor) {
//        return debtorService.addDebtor(debtor);
//    }

    public Debtor updateDebtor(Long id, Debtor debtorUpdate) throws Exception {
        return debtorService.updateDebtor(id, debtorUpdate);
    }

    public Boolean deleteDebtor(Long id) throws Exception {
        debtorService.deleteDebtor(id);
        return true;
    }
}
