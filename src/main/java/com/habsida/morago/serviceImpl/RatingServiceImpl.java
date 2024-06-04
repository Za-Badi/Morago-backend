package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {


    private RatingRepository ratingRepository;


    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingsById(Long id) {
        return ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with id " +id));
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long id, Rating ratingsDetails) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id " + id));

        Double currentGrade = existingRating.getGrade();
        Double newGrade = ratingsDetails.getGrade();


        if (currentGrade != null) {

            newGrade += currentGrade;
        }

        existingRating.setGrade(newGrade);
        existingRating.setWhoUser(ratingsDetails.getWhoUser());
        existingRating.setToWhomUser(ratingsDetails.getToWhomUser());
        existingRating.setCreatedAt(ratingsDetails.getCreatedAt());
        existingRating.setUpdatedAt(ratingsDetails.getUpdatedAt());

        return ratingRepository.save(existingRating);
    }


    @Override
    public void deleteRatings(Long id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(()-> new RuntimeException("Rating not found with id " +id));
        ratingRepository.delete(rating);
    }
}