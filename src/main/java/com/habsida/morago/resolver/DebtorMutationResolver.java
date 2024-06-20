package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebtorMutationResolver implements GraphQLMutationResolver {

    private final DebtorService debtorService;

    @Autowired
    public DebtorMutationResolver(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    public Debtor addDebtor(CreateDebtorInput createDebtorInput) {
        return debtorService.addDebtor(createDebtorInput);
    }

    public Debtor updateDebtor(Long id, UpdateDebtorInput updateDebtorInput) {
        return debtorService.updateDebtor(id, updateDebtorInput);
    }

    public Boolean deleteDebtor(Long id) {
        debtorService.deleteDebtor(id);
        return true;
    }

}