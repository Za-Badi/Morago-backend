
package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.service.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositsMutationResolver implements GraphQLMutationResolver {

    private final DepositsService depositsService;


    @Autowired
    public DepositsMutationResolver(DepositsService depositsService) {
        this.depositsService = depositsService;
    }



    public Deposits addDeposit(CreateDepositsInput createDepositsInput) {
        return depositsService.addDeposit(createDepositsInput);
    }

    public Deposits updateDeposit(Long id, UpdateDepositsInput updateDepositsInput) {
        return depositsService.updateDeposit(id, updateDepositsInput);
    }

    public Boolean deleteDeposit(Long id) {
        depositsService.deleteDeposit(id);
        return true;
    }
}