package com.habsida.morago.controllers;


import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.inputs.WithdrawalInput;
import com.habsida.morago.resolver.WithdrawalsResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WithdrawalController {

    private WithdrawalsResolver withdrawalsResolver;

    public WithdrawalController(WithdrawalsResolver withdrawalsResolver){
        this.withdrawalsResolver = withdrawalsResolver;
    }

    @QueryMapping
    public List<Withdrawals> getAllWithdrawals(){
        return withdrawalsResolver.getAllWithdrawals();
    }

    @QueryMapping
    public Withdrawals getWithdrawalById(@Argument Long id) throws Exception {
        return withdrawalsResolver.getWithdrawalsById(id);
    }

    @MutationMapping
    public Withdrawals addWithdrawal(@Argument WithdrawalInput withdrawalDto){
        return withdrawalsResolver.addWithdrawal(withdrawalDto);
    }

    @MutationMapping
    public  Withdrawals updateWithdrawal (@Argument Long id, @Argument WithdrawalInput withdrawalDto) throws Exception{
        return withdrawalsResolver.updateWithdrawal(id, withdrawalDto);
    }

    @MutationMapping
    public Boolean deleteWithdrawal(@Argument Long id) throws Exception {
        return withdrawalsResolver.deleteWithdrawal(id);
    }

}

