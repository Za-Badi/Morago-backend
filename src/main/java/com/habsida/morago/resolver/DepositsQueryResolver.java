package com.habsida.morago.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.service.DepositsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepositsQueryResolver implements GraphQLQueryResolver {
    private final DepositsService depositsService;

    public DepositsQueryResolver(DepositsService depositsService) {
        this.depositsService = depositsService;
    }

    public DepositsDTO getDepositById(Long id) {
        return depositsService.getDepositById(id);
    }

    public PageOutput<DepositsDTO> getDepositsByStatus(PaymentStatus status, PagingInput pagingInput) {
        return depositsService.getDepositsByStatus(status, pagingInput);
    }

    public PageOutput<DepositsDTO> getDepositsByUserId(Long userId, PagingInput pagingInput) {
        return depositsService.getDepositByUserId(userId, pagingInput);
    }
}
