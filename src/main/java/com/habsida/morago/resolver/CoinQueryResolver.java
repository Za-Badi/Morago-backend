package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.CoinDTO;
import com.habsida.morago.serviceImpl.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoinQueryResolver implements GraphQLQueryResolver {

    private final CoinService coinService;

    public List<CoinDTO> getAllCoins() {
        return coinService.getAllCoins();
    }

    public CoinDTO getCoinById(Long id) {
        return coinService.getByID(id);
    }
}
