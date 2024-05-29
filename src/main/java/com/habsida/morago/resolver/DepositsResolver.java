package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.service.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepositsResolver {
    private final DepositsService depositsService;

    @Autowired
    public DepositsResolver(DepositsService depositsService) {
        this.depositsService = depositsService;
    }

    public List<Deposits> getAllDeposits() {
        return depositsService.getAllDeposits();
    }

    public Optional<Deposits> getDepositById(Long id) throws Exception {
        return depositsService.getDepositById(id);
    }

//    public Deposits addDeposit(Deposits deposit) {
//        return depositsService.addDeposit(deposit);
//    }

//    public Deposits updateDeposit(Long id, Deposits depositUpdate) throws Exception {
//        return depositsService.updateDeposit(id, depositUpdate);
//    }

    public Boolean deleteDeposit(Long id) throws Exception {
        depositsService.deleteDeposits(id);
        return true;
    }
}
