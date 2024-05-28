package com.habsida.morago.service;

import com.habsida.morago.model.entity.Ratings;

import java.util.List;
import java.util.Optional;

public interface RatingsService {
    public List<Ratings> getAllRatings();
    public Optional<Ratings> getRatingsById(Long id);

    public Ratings createRatings(Ratings ratings);

    public Ratings updateRatings(Long id, Ratings ratingsDetails);

    public void deleteRatings(Long id);

}
