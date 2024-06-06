package com.habsida.morago.resolver;


import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RatingResolver {
    private final RatingService ratingService;

//    @QueryMapping
//    public List<Rating> getAllRatings() {
//        return ratingService.getAllRatings();
//    }

    @MutationMapping(name = "createRating")
    public Rating createRating(@Argument("ratingInput") RatingInput ratingInput) throws Exception {
        return ratingService.createRating(ratingInput);
    }

    //    @MutationMapping(name = "upDateRating")
//    public Rating updateRating(@Argument Long id, @Argument Double grade) {
//        RatingInput ratingInput = new RatingInput();
//        ratingInput.setGrade(grade);
//        return ratingService.updateRating(id, ratingInput);
//    }
    @MutationMapping(name = "updateRating")
    public Rating updateRating(@Argument Long id, @Argument Double grade) {

        RatingInput ratingInput = new RatingInput();
        ratingInput.setCallId(id);
        ratingInput.setGrade(grade);

        return ratingService.updateRating(id, ratingInput);
    }


    @MutationMapping(name = "deleteRating")
    public Boolean deleteRating(@Argument Long id) {
        try {
            ratingService.deleteRating(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}