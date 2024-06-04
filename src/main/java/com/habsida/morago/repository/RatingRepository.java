package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RatingRepository extends JpaRepository<Rating, Long> {
}