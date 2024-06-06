package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;
    private final UserRepository userRepository;

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
        Rating rating = new Rating();

        rating.setId(ratingInput.getCallId());

        User whoUser = userService.getUserById(ratingInput.getWhoUserId());
        if (whoUser == null) {
            throw new RuntimeException("WhoUser not found with ID: " + ratingInput.getWhoUserId());
        }
        User toWhomUser = userService.getUserById(ratingInput.getToWhomUserId());
        if (toWhomUser == null) {
            throw new RuntimeException("ToWhomUser not found with ID: " + ratingInput.getToWhomUserId());
        }

        rating.setWhoUser(whoUser);
        rating.setToWhomUser(toWhomUser);
        rating.setRatings(ratingInput.getGrade());

        return ratingRepository.save(rating);
    }



    @Override
    public Rating updateRating(Long id, RatingInput ratingInput) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id " + id));

        Double currentGrade = existingRating.getRatings();
        Double newGrade = ratingInput.getGrade();

        if (currentGrade != null) {
            newGrade += currentGrade;
        }

        existingRating.setRatings(newGrade);

        User toWhomUser = userRepository.findById(ratingInput.getToWhomUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + ratingInput.getToWhomUserId()));

        Double currentUserRating = existingRating.getRatings();
        if (currentUserRating != null) {
            currentUserRating += newGrade;
        } else {
            currentUserRating = newGrade;
        }
        existingRating.setRatings(currentUserRating);

        userRepository.save(toWhomUser);

        User whoUser = userRepository.findById(ratingInput.getWhoUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + ratingInput.getWhoUserId()));
        existingRating.setWhoUser(whoUser);
        existingRating.setToWhomUser(toWhomUser);

        return ratingRepository.save(existingRating);
    }



    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id " + id));
        ratingRepository.delete(rating);
    }
}