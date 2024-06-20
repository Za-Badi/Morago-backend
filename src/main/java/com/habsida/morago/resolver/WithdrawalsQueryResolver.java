package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WithdrawalsQueryResolver implements GraphQLQueryResolver {
    private WithdrawalService withdrawalService;

    public WithdrawalsQueryResolver(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    public List<Withdrawals> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    public Withdrawals getWithdrawalsById(Long id) {
        return withdrawalService.getWithdrawalById(id);
    }

    public List<Withdrawals> getWithdrawalsByStatus(PaymentStatus status){
        return withdrawalService.getWithdrawalsByStatus(status);
    }

    public List<Withdrawals> getWithdrawalsByUserId(Long userId){
        return withdrawalService.getWithdrawalsByUserId(userId);
    }

}