package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Coin;
import com.habsida.morago.model.inputs.CreateCoinInput;
import com.habsida.morago.model.inputs.UpdateCoinInput;
import com.habsida.morago.serviceImpl.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CoinResolver {

    private final CoinService coinService;

    @MutationMapping
    public Coin createCoin(@Argument CreateCoinInput input) {
        return coinService.createCoin(input);
    }

    @MutationMapping
    public Coin updateCoin(@Argument UpdateCoinInput input) {
        return coinService.updateCoin(input);
    }

    @MutationMapping
    public Boolean deleteCoin(@Argument Long id) {
        return coinService.deleteCoin(id);
    }

    @QueryMapping
    public Coin getCoinById(@Argument Long id) {
        return coinService.getByID(id);
    }

    @QueryMapping
    public List<Coin> getAllCoins() {
        return coinService.getAllCoins();
    }
}
