package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WithdrawalsMutationResolver implements GraphQLMutationResolver {
    private WithdrawalService withdrawalService;

    public WithdrawalsMutationResolver(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    public Withdrawals addWithdrawal(CreateWithdrawalInput withdrawalDto) {
        return withdrawalService.addWithdrawal(withdrawalDto);
    }

    public Withdrawals updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput) {
        return withdrawalService.updateWithdrawal(id, updateWithdrawalInput);
    }

    public Boolean deleteWithdrawal(Long id) {
        withdrawalService.deleteWithdrawal(id);
        return true;
    }

}