package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.repository.CallRespository;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final CallRespository callRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found with id " + id));
    }

    @Override
    public Rating createRating(RatingInput ratingInput) throws Exception {
        User whoUser = userRepository.findById(ratingInput.getWhoUserId()).orElseThrow(() -> new RuntimeException("not found"));
        User toWhom = userRepository.findById(ratingInput.getToWhomUserId()).orElseThrow(() -> new RuntimeException("user not found"));

        Rating rating = new Rating();
        rating.setWhoUser(whoUser);
        rating.setToWhomUser(toWhom);
        rating.setRatings(ratingInput.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }


    @Override
    public Rating updateRating(Long id, RatingInput ratingInput) {
        Rating existingRating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found with id " + id));


        existingRating.setRatings(ratingInput.getRating());


        return ratingRepository.save(existingRating);
    }


    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id " + id));
        ratingRepository.delete(rating);
    }
}