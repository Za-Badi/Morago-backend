package com.habsida.morago.service;

import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;
import com.habsida.morago.model.results.PageOutput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RatingService {
    PageOutput<RatingDTO> getAllRatings(PagingInput pagingInput);
    RatingDTO getRatingById(Long id);
    RatingDTO createRating(RatingInput ratingInput) throws Exception;
    void deleteRating(Long id) throws Exception;
    Double getAverageRating(Long toWhomUserId);
    RatingDTO updateRating(Long id, UpdateRatingInput updateRating) throws Exception;
}
