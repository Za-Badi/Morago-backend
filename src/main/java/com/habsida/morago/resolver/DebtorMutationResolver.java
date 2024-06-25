package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.DebtorDTO;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.service.DebtorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DebtorMutationResolver implements GraphQLMutationResolver {

    private final DebtorService debtorService;
    private final ModelMapper modelMapper;

    public DebtorDTO addDebtor(CreateDebtorInput createDebtorInput) {
        return debtorService.addDebtor(createDebtorInput);
    }

    public DebtorDTO updateDebtor(Long id, UpdateDebtorInput updateDebtorInput) {
        return debtorService.updateDebtor(id, updateDebtorInput);
    }

    public Boolean deleteDebtor(Long id) {
        debtorService.deleteDebtor(id);
        return true;
    }
}
