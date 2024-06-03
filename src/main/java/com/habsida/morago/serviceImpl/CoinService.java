package com.habsida.morago.serviceImpl;


import com.habsida.morago.model.entity.Coin;
import com.habsida.morago.model.inputs.CreateCoinInput;
import com.habsida.morago.model.inputs.UpdateCoinInput;
import com.habsida.morago.repository.CoinRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class CoinService {
    private final CoinRespository repository;

    public Coin createCoin( CreateCoinInput input) {
        Coin coins = new Coin();
        coins.setCoin(input.getCoin());
        coins.setWon(input.getWon());
        return repository.save(coins);
    }

    public Coin getByID(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Coin updateCoin(UpdateCoinInput input) {
        Coin coins = getByID(input.getId());
        if (input.getCoin() != null && input.getCoin()> 0) {
            coins.setCoin(input.getCoin());
        }
        if (input.getWon() != null && input.getWon()>0) {
            coins.setWon(input.getWon());
        }
        return repository.save(coins);
    }

    public List<Coin> getAllCoins() {
        return repository.findAll();
    }

    public Boolean deleteCoin(Long id) {
        Coin coin = getByID(id);
        repository.delete(coin);
        return true;
    }


}
