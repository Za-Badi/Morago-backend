package com.habsida.morago.service;

import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;


import java.util.List;
import java.util.Optional;
public interface RatingService {
    List<Rating> getAllRatings();
    Rating getRatingById(Long id);
    Rating createRating(RatingInput ratingInput) throws Exception;

    void deleteRating(Long id) throws Exception;
    public Double getAverageRating(Long toWhomUserId);
    Rating updateRating(Long id, UpdateRatingInput updateRating) throws Exception;

}