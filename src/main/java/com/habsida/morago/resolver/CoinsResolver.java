package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.entity.Coins;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.service.CoinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoinsResolver implements GraphQLMutationResolver {

    private final CoinsService coinsService;

    public Coins createCoins(Double coin, Double won) {

        System.out.println("zaha "+coin+"  "+won);
        return coinsService.create(coin, won);
    }
    public Coins updateCoins(Long id, Double coin, Double won) {
        return coinsService.update(id, coin, won);
    }
    public Boolean deleteAppVersion(Long id) {
        return coinsService.delete(id);
    }




}
