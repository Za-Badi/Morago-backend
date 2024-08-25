package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.DebtorDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.service.DebtorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DebtorQueryResolver implements GraphQLQueryResolver {

    private final DebtorService debtorService;

    public PageOutput<DebtorDTO> getAllDebtors(PagingInput pagingInput) {
        return debtorService.getAllDebtors(pagingInput);
    }

    public DebtorDTO getDebtorById(Long id) {
        return debtorService.getDebtorById(id);
    }

    public List<DebtorDTO> getDebtorByUserId(Long userId) {
        return debtorService.getDebtorByUserId(userId);
    }
}
