package com.habsida.morago.service;


import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.entity.Coins;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.repository.CoinsRespository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class CoinsService {
    private final CoinsRespository repository;

    public Coins create(Double coin, Double won) {
        Coins coins = new Coins();
        coins.setCoin(coin);
        coins.setWon(won);
        return repository.save(coins);
    }

    public Coins getByID(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Coins update(Long id, Double coin, Double won) {
        Coins coins = getByID(id);
        if (coin != null && coin>0) {
            coins.setCoin(coin);
        }
        if (won != null && won>0) {
            coins.setWon(won);
        }
        return repository.save(coins);
    }

    public List<Coins> getAll() {
        return repository.findAll();
    }

    public Boolean delete(Long id) {
        Coins coins = getByID(id);
        repository.delete(coins);
        return true;
    }


}
