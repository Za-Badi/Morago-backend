//package com.habsida.morago.resolver;
//
//import com.habsida.morago.model.entity.Rating;
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.inputs.RatingInput;
//import com.habsida.morago.service.RatingService;
//import com.habsida.morago.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//@Component
//@RequiredArgsConstructor
//public class RatingResolver {
//    private final RatingService ratingService;
//    private final UserRepository userRepository;
//
//    @QueryMapping(name = "getAllRatings")
//    public List<Rating> getAllRatings() {
//        return ratingService.getAllRatings();
//    }
//
//    @QueryMapping(name = "getRatingById")
//    public Rating getRatingById(@Argument Long id) throws Exception {
//        return ratingService.getRatingById(id);
//    }
//
//    @MutationMapping(name = "createRating")
//    public Rating createRating(@Argument("RatingDto") RatingInput ratingInput) throws Exception {
//        User whoUser = userRepository.findById(ratingInput.getWhoUserId()).orElseThrow(() -> new RuntimeException("WhoUser not found"));
//        User toWhomUser = userRepository.findById(ratingInput.getToWhomUserId()).orElseThrow(() -> new RuntimeException("ToWhomUser not found"));
//
//        Rating rating = new Rating();
//        rating.setWhoUser(whoUser);
//        rating.setToWhomUser(toWhomUser);
//        rating.setRatings(ratingInput.getRating());
//
//        return ratingService.createRating(ratingInput);
//    }
//
//
//
//    @MutationMapping(name = "deleteRating")
//    public Boolean deleteRating(@Argument Long id) throws Exception {
//        ratingService.deleteRating(id);
//        return true;
//    }
//}