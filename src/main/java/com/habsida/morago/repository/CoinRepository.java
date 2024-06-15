package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
}
