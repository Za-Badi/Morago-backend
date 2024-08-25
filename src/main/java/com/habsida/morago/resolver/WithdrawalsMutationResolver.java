package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WithdrawalsMutationResolver implements GraphQLMutationResolver {
    private final WithdrawalService withdrawalService;

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