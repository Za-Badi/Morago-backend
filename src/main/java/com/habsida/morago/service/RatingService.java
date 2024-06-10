package com.habsida.morago.service;

import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.inputs.RatingInput;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> getAllRatings();

    Rating getRatingById(Long id);

    Rating createRating(RatingInput ratingInput) throws Exception;

    Rating updateRating(Long id, RatingInput ratingInput);

    void deleteRating(Long id) throws Exception;
}