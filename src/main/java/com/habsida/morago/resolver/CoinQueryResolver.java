package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.CoinDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.CoinPageOutput;
import com.habsida.morago.serviceImpl.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoinQueryResolver implements GraphQLQueryResolver {

    private final CoinService coinService;

    public CoinPageOutput getAllCoins(PagingInput pagingInput) {
        return coinService.getAllCoins(pagingInput);
    }

    public CoinDTO getCoinById(Long id) {
        return coinService.getByID(id);
    }
}
