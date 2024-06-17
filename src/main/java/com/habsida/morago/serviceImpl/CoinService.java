package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.Coin;
import com.habsida.morago.model.inputs.CreateCoinInput;
import com.habsida.morago.model.inputs.UpdateCoinInput;
import com.habsida.morago.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CoinService {
    private final CoinRepository repository;

    public Coin createCoin(CreateCoinInput input) {
        Coin coins = new Coin();
        if (input.getCoin() != 0.0) {
            coins.setCoin(input.getCoin());
        } else {
            throw new ExceptionGraphql("Enter valid double for coin");
        }
        if (input.getWon() != 0.0) {
            coins.setWon(input.getWon());
        } else {
            throw new ExceptionGraphql("Enter valid double for won");
        }
        return repository.save(coins);
    }

    public Coin getByID(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("No Coin with id: "+ id));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Coin updateCoin(UpdateCoinInput input) {
        Coin coins = getByID(input.getId());
        if (input.getCoin() != null && input.getCoin() > 0) {
            coins.setCoin(input.getCoin());
        }
        if (input.getWon() != null && input.getWon() > 0) {
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
