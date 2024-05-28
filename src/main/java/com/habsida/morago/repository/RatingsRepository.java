package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RatingsRepository extends JpaRepository<Ratings, Long> {
}
