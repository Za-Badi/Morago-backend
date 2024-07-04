package com.habsida.morago.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.service.DepositsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepositsQueryResolver implements GraphQLQueryResolver {
    private final DepositsService depositsService;

    public DepositsQueryResolver(DepositsService depositsService) {
        this.depositsService = depositsService;
    }

    public List<DepositsDTO> getAllDeposits() {
        return depositsService.getAllDeposits();
    }

    public DepositsDTO getDepositById(Long id) {
        return depositsService.getDepositById(id);
    }

    public List<DepositsDTO> getDepositsByStatus(PaymentStatus status) {
        return depositsService.getDepositsByStatus(status);
    }

    public List<DepositsDTO> getDepositsByUserId(Long userId) {
        return depositsService.getDepositByUserId(userId);
    }
}
