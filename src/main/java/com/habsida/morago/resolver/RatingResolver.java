package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RatingResolver {
    private final CallService callService;
    private final RatingService ratingsService;
    private final UserRepository userRepository;


    @MutationMapping(name = "createRating")
    public Rating createRating(@Argument Long callId, @Argument Long whoUserId, @Argument Long toWhomUserId, @Argument Double grade) {
        User whoUser = userRepository.findById(whoUserId).orElseThrow(()-> new RuntimeException("User not found"));
        User toWhomUser = userRepository.findById(toWhomUserId).orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = new Rating();
        rating.setWhoUser(whoUser);
        rating.setToWhomUser(toWhomUser);
        rating.setGrade(grade);
        return ratingsService.createRating(rating);
    }

    @MutationMapping(name = "updateRating")
    public Rating updateRating(@Argument Long id, @Argument Double grade) {
        Rating ratingDetails = new Rating();
        ratingDetails.setGrade(grade);
        return ratingsService.updateRating(id, ratingDetails);
    }

    @MutationMapping(name = "deleteRating")
    public Boolean deleteRating(@Argument Long id) {
        ratingsService.deleteRatings(id);
        return true;
    }
}