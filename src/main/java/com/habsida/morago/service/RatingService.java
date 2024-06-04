package com.habsida.morago.service;

import com.habsida.morago.model.entity.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    public List<Rating> getAllRatings();
    public Rating getRatingsById(Long id);

    public Rating createRating(Rating ratings);

    public Rating updateRating(Long id, Rating ratingsDetails);

    public void deleteRatings(Long id);

}