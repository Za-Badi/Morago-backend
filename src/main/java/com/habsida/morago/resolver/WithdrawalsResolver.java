package com.habsida.morago.resolver;

import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WithdrawalsResolver {
    private WithdrawalService withdrawalService;

    public WithdrawalsResolver(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    public List<WithdrawalsDTO> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    public WithdrawalsDTO getWithdrawalsById(Long id) {
        return withdrawalService.getWithdrawalById(id);
    }

    public List<WithdrawalsDTO> getWithdrawalsByStatus(PaymentStatus status){
        return withdrawalService.getWithdrawalsByStatus(status);
    }

    public List<WithdrawalsDTO> getWithdrawalsByUserId(Long userId){
        return withdrawalService.getWithdrawalsByUserId(userId);
    }

    public WithdrawalsDTO addWithdrawal(CreateWithdrawalInput withdrawalDto) {
        return withdrawalService.addWithdrawal(withdrawalDto);
    }

    public WithdrawalsDTO updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput) {
        return withdrawalService.updateWithdrawal(id, updateWithdrawalInput);
    }

    public Boolean deleteWithdrawal(Long id) {
        withdrawalService.deleteWithdrawal(id);
        return true;
    }

}
