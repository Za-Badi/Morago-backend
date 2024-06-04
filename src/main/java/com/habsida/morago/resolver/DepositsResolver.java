
package com.habsida.morago.resolver;

import com.habsida.morago.dtos.DepositsInput;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.services.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Deposits getDepositById(Long id) throws Exception {
        return depositsService.getDepositById(id);
    }

    public Deposits addDeposit(DepositsInput input) {
        return depositsService.addDeposit(input);
    }

    public Deposits updateDeposit(Long id, DepositsInput input) throws Exception {
        return depositsService.updateDeposit(id, input);
    }

    public Boolean deleteDeposit(Long id) throws Exception {
        depositsService.deleteDeposit(id);
        return true;
    }
}