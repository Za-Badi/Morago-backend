package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.CoinDTO;
import com.habsida.morago.model.entity.Coin;
import com.habsida.morago.model.inputs.CreateCoinInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateCoinInput;
import com.habsida.morago.model.results.CoinPageOutput;
import com.habsida.morago.repository.CoinRepository;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CoinService {
    private final CoinRepository repository;
    private final ModelMapperUtil modelMapperUtil;

    public CoinDTO createCoin(CreateCoinInput input) {
        if (input.getCoin() <= 0.0) {
            throw new ExceptionGraphql("Enter valid positive double for coin");
        }
        if (input.getWon() <= 0.0) {
            throw new ExceptionGraphql("Enter valid positive double for won");
        }

        Coin coins = new Coin();
        coins.setCoin(input.getCoin());
        coins.setWon(input.getWon());

        Coin savedCoin = repository.save(coins);
        return modelMapperUtil.map(savedCoin, CoinDTO.class);
    }

    @Transactional(readOnly = true)
    public CoinDTO getByID(Long id) {
        Coin coin = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Coin with id: " + id));
        return modelMapperUtil.map(coin, CoinDTO.class);
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
        return modelMapperUtil.map(updatedCoin, CoinDTO.class);
    }

    @Transactional(readOnly = true)
    public CoinPageOutput getAllCoins(PagingInput pagingInput) {
        Page<Coin> page = repository.findAll(PageUtil.buildPageable(pagingInput));
        return new CoinPageOutput(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.map(coin -> modelMapperUtil.map(coin, CoinDTO.class)).getContent()
        );
    }

    public boolean deleteCoin(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
