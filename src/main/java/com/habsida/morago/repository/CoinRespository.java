package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRespository extends JpaRepository<Coin, Long> {
}
