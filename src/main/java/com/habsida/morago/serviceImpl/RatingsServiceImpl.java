package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Ratings;
import com.habsida.morago.repository.RatingsRepository;
import com.habsida.morago.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingsServiceImpl implements RatingsService {

    @Autowired
    private  RatingsRepository ratingsRepository;


    @Override
    public List<Ratings> getAllRatings() {
        return ratingsRepository.findAll();
    }

    @Override
    public Optional<Ratings> getRatingsById(Long id) {

        return ratingsRepository.findById(id);
    }

    @Override
    public Ratings createRatings(Ratings ratings) {
        return ratingsRepository.save(ratings);
    }

    @Override
    public Ratings updateRatings(Long id, Ratings ratingsDetails) {
        Optional<Ratings> optionalRatings = ratingsRepository.findById(id);

        if(optionalRatings.isPresent()) {
            Ratings ratings = optionalRatings.get();
            ratings.setWhoUser(ratingsDetails.getWhoUser());
            ratings.setToWhomUser(ratingsDetails.getToWhomUser());
            ratings.setGrade(ratingsDetails.getGrade());
            ratings.setCreatedAt(ratingsDetails.getCreatedAt());
            ratings.setUpdatedAt(ratingsDetails.getUpdatedAt());
            return ratingsRepository.save(ratings);
        }
        return null;
    }

    @Override
    public void deleteRatings(Long id) {
      ratingsRepository.deleteById(id);
    }
}
