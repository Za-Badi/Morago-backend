package com.habsida.morago.resolver;

//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.Coins;
import com.habsida.morago.serviceImpl.CoinsService;
//import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CoinsResolver  {

    private final CoinsService coinsService;
    @MutationMapping
    public Coins createCoins(@Argument  Double coin,@Argument  Double won) {

        System.out.println("zaha "+coin+"  "+won);
        return coinsService.create(coin, won);
    }
    @MutationMapping
    public Coins updateCoins(@Argument  Long id,@Argument  Double coin,@Argument  Double won) {
        return coinsService.update(id, coin, won);
    }
    @MutationMapping
    public Boolean deleteCoins(Long id) {
        return coinsService.delete(id);
    }




}
