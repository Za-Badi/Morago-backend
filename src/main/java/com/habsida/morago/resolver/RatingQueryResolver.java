package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RatingQueryResolver implements GraphQLQueryResolver {
    private final RatingService ratingService;

    public PageOutput<RatingDTO> getAllRatings(PagingInput pagingInput) {
        return ratingService.getAllRatings(pagingInput);
    }

    public RatingDTO getRatingById(Long id) {
        return ratingService.getRatingById(id);
    }

    public Double getAverageRating(Long toWhomUserId) {
        return ratingService.getAverageRating(toWhomUserId);
    }
}
