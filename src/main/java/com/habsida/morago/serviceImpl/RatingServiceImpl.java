package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;
import com.habsida.morago.repository.CallRepository;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CallRepository callRepository;

    @Override
    @Transactional
    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(rating -> modelMapper.map(rating, RatingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RatingDTO getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Rating not found with id " + id));
        return modelMapper.map(rating, RatingDTO.class);
    }

    @Override
    @Transactional
    public RatingDTO createRating(RatingInput ratingInput) throws Exception {
        User whoUser = userRepository.findById(ratingInput.getWhoUserId())
                .orElseThrow(() -> new GraphqlException("User who has rated not found"));
        User toWhom = userRepository.findById(ratingInput.getToWhomUserId())
                .orElseThrow(() -> new GraphqlException("User who has been rated not found"));

        Rating rating = new Rating();
        rating.setWhoUser(whoUser);
        rating.setToWhomUser(toWhom);
        rating.setRatings(ratingInput.getRatings());
        rating.setCreatedAt(LocalDateTime.now());
        Rating savedRating = ratingRepository.save(rating);

        return modelMapper.map(savedRating, RatingDTO.class);
    }

    @Override
    @Transactional
    public RatingDTO updateRating(Long id, UpdateRatingInput update) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Rating not found"));

        existingRating.setRatings(update.getRatings());
        existingRating.setUpdatedAt(LocalDateTime.now());
        Rating updatedRating = ratingRepository.save(existingRating);

        return modelMapper.map(updatedRating, RatingDTO.class);
    }

    @Override
    @Transactional
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Double getAverageRating(Long toWhomUserId) {
        Double averageRating = ratingRepository.findAverageGradeByToWhomUserId(toWhomUserId);

        if (averageRating == null || averageRating.isNaN()) {
            throw new GraphqlException("Rating with id " + toWhomUserId + " not found");
        }

        return averageRating;
    }
}
