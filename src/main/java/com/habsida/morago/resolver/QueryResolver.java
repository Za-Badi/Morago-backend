package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.entity.Coins;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.service.AppVersionService;
import com.habsida.morago.service.CoinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final AppVersionService appVersionService;
    private final CoinsService coinsService;

    public Coins getCoin(Long id) {
        return coinsService.getByID(id);
    }
    public List<Coins> getAllCoins() {
        return coinsService.getAll();
    }

    public AppVersion getAppVersionByPlatform(EPlatform platform) {
        return appVersionService.getByPlatform(platform);
    }
    public List<AppVersion> getAllAppVersions() {
        return appVersionService.getAll();
    }

    public String test(String t){
        return "zaha";
    }

}


