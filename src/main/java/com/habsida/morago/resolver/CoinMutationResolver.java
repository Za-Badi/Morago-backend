package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.CoinDTO;
import com.habsida.morago.model.inputs.CreateCoinInput;
import com.habsida.morago.model.inputs.UpdateCoinInput;
import com.habsida.morago.serviceImpl.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoinMutationResolver implements GraphQLMutationResolver {

    private final CoinService coinService;

    public CoinDTO createCoin(CreateCoinInput input) throws ExceptionGraphql {
        return coinService.createCoin(input);
    }

    public CoinDTO updateCoin(UpdateCoinInput input) {
        return coinService.updateCoin(input);
    }

    public Boolean deleteCoin(Long id) {
        return coinService.deleteCoin(id);
    }
}
