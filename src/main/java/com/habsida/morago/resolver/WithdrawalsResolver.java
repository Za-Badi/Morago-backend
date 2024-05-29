package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WithdrawalsResolver  {
    private final WithdrawalService withdrawalsService;

    @Autowired
    public WithdrawalsResolver(WithdrawalService withdrawalsService) {
        this.withdrawalsService = withdrawalsService;
    }

    public List<Withdrawals> getAllWithdrawals() {
        return withdrawalsService.getAllWithdrawals();
    }

    public Optional<Withdrawals> getWithdrawalById(Long id) throws Exception {
        return withdrawalsService.getWithdrawalsById(id);
    }

//    public Withdrawals addWithdrawal(Withdrawals withdrawal) {
//        return withdrawalsService.addWithdrawal(withdrawal);
//    }

    public Withdrawals updateWithdrawal(Long id, Withdrawals withdrawalUpdate) throws Exception {
        return withdrawalsService.updateWithdrawals(id, withdrawalUpdate);
    }

    public Boolean deleteWithdrawal(Long id) throws Exception {
        withdrawalsService.deleteWithdrawals(id);
        return true;
    }
}
