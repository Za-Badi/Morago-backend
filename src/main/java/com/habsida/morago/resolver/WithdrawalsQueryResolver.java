package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WithdrawalsQueryResolver implements GraphQLQueryResolver {
    private WithdrawalService withdrawalService;

    public PageOutput<WithdrawalsDTO> getAllWithdrawals(PagingInput pagingInput) {
        return withdrawalService.getAllWithdrawals(pagingInput);
    }

    public WithdrawalsDTO getWithdrawalsById(Long id) {
        return withdrawalService.getWithdrawalById(id);
    }

    public PageOutput<WithdrawalsDTO> getWithdrawalsByStatus(PaymentStatus status, PagingInput pagingInput){
        return withdrawalService.getWithdrawalsByStatus(status, pagingInput);
    }

    public PageOutput<WithdrawalsDTO> getWithdrawalsByUserId(Long userId, PagingInput pagingInput){
        return withdrawalService.getWithdrawalsByUserId(userId, pagingInput);
    }

}