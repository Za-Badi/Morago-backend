
package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.service.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepositsQueryResolver implements GraphQLQueryResolver {

    private final DepositsService depositsService;


    @Autowired
    public DepositsQueryResolver(DepositsService depositsService) {
        this.depositsService = depositsService;
    }

    public List<Deposits> getDepositsByStatus(PaymentStatus status) {
        return depositsService.getDepositsByStatus(status);
    }

    public List<Deposits> getDepositsByUserId(Long userId) {
        return depositsService.getDepositByUserId(userId);
    }


    public List<Deposits> getAllDeposits() {
        return depositsService.getAllDeposits();
    }

    public Deposits getDepositById(Long id) {
        return depositsService.getDepositById(id);
    }
}