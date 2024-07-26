package com.habsida.morago.service;

import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;

import java.util.List;

public interface RatingService {
    List<RatingDTO> getAllRatings();
    RatingDTO getRatingById(Long id);
    RatingDTO createRating(RatingInput ratingInput) throws Exception;
    void deleteRating(Long id) throws Exception;
    Double getAverageRating(Long toWhomUserId);
    RatingDTO updateRating(Long id, UpdateRatingInput updateRating) throws Exception;
}
