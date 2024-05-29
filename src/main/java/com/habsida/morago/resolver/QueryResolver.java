package com.habsida.morago.resolver;

//import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.entity.Coins;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.serviceImpl.AppVersionService;
import com.habsida.morago.serviceImpl.CoinsService;
//import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class QueryResolver  {

    private final AppVersionService appVersionService;
    private final CoinsService coinsService;
    @QueryMapping
    public Coins getCoin(@Argument Long id) {
        return coinsService.getByID(id);
    }
    @QueryMapping
    public List<Coins> getAllCoins() {
        return coinsService.getAll();
    }
    @QueryMapping
    public AppVersion getAppVersionByPlatform(@Argument EPlatform platform) {
        return appVersionService.getByPlatform(platform);
    }
    @QueryMapping
    public List<AppVersion> getAllAppVersions() {
        return appVersionService.getAll();
    }
    @QueryMapping
    public String test(@Argument String t){
        return "zaha";
    }



}


