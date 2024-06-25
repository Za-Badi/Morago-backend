package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.CoinDTO;
import com.habsida.morago.model.entity.Coin;
import com.habsida.morago.model.inputs.CreateCoinInput;
import com.habsida.morago.model.inputs.UpdateCoinInput;
import com.habsida.morago.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinService {
    private final CoinRepository repository;
    private final ModelMapper modelMapper;

    public CoinDTO createCoin(CreateCoinInput input) {
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
        Coin savedCoin = repository.save(coins);
        return modelMapper.map(savedCoin, CoinDTO.class);
    }

    public CoinDTO getByID(Long id) {
        Coin coin = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Coin with id: " + id));
        return modelMapper.map(coin, CoinDTO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public CoinDTO updateCoin(UpdateCoinInput input) {
        Coin coins = repository.findById(input.getId())
                .orElseThrow(() -> new EntityNotFoundException("No Coin with id: " + input.getId()));
        if (input.getCoin() != null && input.getCoin() > 0) {
            coins.setCoin(input.getCoin());
        }
        if (input.getWon() != null && input.getWon() > 0) {
            coins.setWon(input.getWon());
        }
        Coin updatedCoin = repository.save(coins);
        return modelMapper.map(updatedCoin, CoinDTO.class);
    }

    public List<CoinDTO> getAllCoins() {
        return repository.findAll()
                .stream()
                .map(coin -> modelMapper.map(coin, CoinDTO.class))
                .collect(Collectors.toList());
    }

    public Boolean deleteCoin(Long id) {
        Coin coin = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Coin with id: " + id));
        repository.delete(coin);
        return true;
    }
}
