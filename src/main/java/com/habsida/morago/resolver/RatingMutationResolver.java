package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingMutationResolver implements GraphQLMutationResolver {

    private final RatingService ratingService;

    public RatingDTO createRating(RatingInput ratingInput) throws Exception {
        return ratingService.createRating(ratingInput);
    }

    public RatingDTO updateRating(Long id, UpdateRatingInput update) throws Exception {
        return ratingService.updateRating(id, update);
    }

    public Boolean deleteRating(Long id) throws Exception {
        ratingService.deleteRating(id);
        return true;
    }
}
