package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.service.DepositsService;
import org.springframework.stereotype.Component;

@Component
public class DepositsMutationResolver implements GraphQLMutationResolver {
    private final DepositsService depositsService;

    public DepositsMutationResolver(DepositsService depositsService) {
        this.depositsService = depositsService;
    }

    public DepositsDTO addDeposit(CreateDepositsInput createDepositsInput) {
        return depositsService.addDeposit(createDepositsInput);
    }

    public DepositsDTO updateDeposit(Long id, UpdateDepositsInput updateDepositsInput) {
        return depositsService.updateDeposit(id, updateDepositsInput);
    }

    public Boolean deleteDeposit(Long id) {
        depositsService.deleteDeposit(id);
        return true;
    }
}
