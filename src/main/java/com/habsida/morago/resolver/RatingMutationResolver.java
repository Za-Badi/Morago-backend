package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RatingMutationResolver implements GraphQLMutationResolver {

    private final RatingService ratingService;
    private final RatingRepository ratingRepository;

    public Rating createRating(RatingInput ratingInput) throws Exception {
        return ratingService.createRating(ratingInput);
    }

    public Rating updateRating(Long id, UpdateRatingInput update) throws Exception{
        Rating existingRating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found with id " + id));


        existingRating.setRatings(update.getRatings());
        existingRating.setUpdatedAt(LocalDateTime.now());


        return ratingRepository.save(existingRating);
    }
    public void deleteRating(Long id) throws Exception{
        ratingService.deleteRating(id);
    }

}
