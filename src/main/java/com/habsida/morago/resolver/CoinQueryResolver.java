package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.Coin;
import com.habsida.morago.serviceImpl.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoinQueryResolver implements GraphQLQueryResolver {

    private final CoinService coinService;
    public List<Coin> getAllCoins() {
        return coinService.getAllCoins();
    }
    public Coin getCoinById( Long id) {
        return coinService.getByID(id);
    }

}
