package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.inputs.WithdrawalInput;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WithdrawalsResolver {
    private WithdrawalService withdrawalService;

    public WithdrawalsResolver(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    public List<Withdrawals> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    public Withdrawals getWithdrawalsById(Long id) throws Exception {
        return withdrawalService.getWithdrawalById(id);
    }

    public Withdrawals addWithdrawal(WithdrawalInput withdrawalDto) {
        return withdrawalService.addWithdrawal(withdrawalDto);
    }

    public Withdrawals updateWithdrawal(Long id, WithdrawalInput withdrawalDto) throws Exception {
        return withdrawalService.updateWithdrawal(id, withdrawalDto);
    }

    public Boolean deleteWithdrawal(Long id) throws Exception {
        withdrawalService.deleteWithdrawal(id);
        return true;
    }

}