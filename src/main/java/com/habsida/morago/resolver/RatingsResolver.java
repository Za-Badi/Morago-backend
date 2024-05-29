package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Ratings;
import com.habsida.morago.service.RatingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RatingsResolver  {

    private final RatingsService ratingsService;

    public Ratings createRating(Ratings ratings) {
        return ratingsService.createRatings(ratings);
    }

    public Ratings updateRating(Long id, Ratings ratingsDetails) {
        return ratingsService.updateRatings(id, ratingsDetails);
    }

    public void deleteRating(Long id) {
        ratingsService.deleteRatings(id);
    }

    public Ratings getRating(Long id) {
        return ratingsService.getRatingsById(id).orElseThrow(() -> new RuntimeException("resource not fot found"));
    }

    public List<Ratings> getAllRatings() {
        return ratingsService.getAllRatings();
    }
}