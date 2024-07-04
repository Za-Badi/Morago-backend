package com.habsida.morago.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.entity.Rating;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.RatingInput;
import com.habsida.morago.model.inputs.UpdateRatingInput;
import com.habsida.morago.repository.RatingRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class RatingServiceImplTest {
    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRatings() {
        Rating rating = new Rating();
        List<Rating> ratings = Arrays.asList(rating);

        when(ratingRepository.findAll()).thenReturn(ratings);
        when(modelMapper.map(any(Rating.class), eq(RatingDTO.class))).thenReturn(new RatingDTO());

        List<RatingDTO> result = ratingService.getAllRatings();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetRatingById() {
        Rating rating = new Rating();
        when(ratingRepository.findById(1L)).thenReturn(Optional.of(rating));
        when(modelMapper.map(any(Rating.class), eq(RatingDTO.class))).thenReturn(new RatingDTO());

        RatingDTO result = ratingService.getRatingById(1L);

        assertNotNull(result);
    }

    @Test
    void testCreateRating() throws Exception {
        RatingInput input = new RatingInput();
        input.setWhoUserId(1L);
        input.setToWhomUserId(2L);
        input.setRatings(5.0);

        User whoUser = new User();
        User toWhomUser = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(whoUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(toWhomUser));
        when(ratingRepository.save(any(Rating.class))).thenReturn(new Rating());
        when(modelMapper.map(any(Rating.class), eq(RatingDTO.class))).thenReturn(new RatingDTO());

        RatingDTO result = ratingService.createRating(input);

        assertNotNull(result);
    }

    @Test
    void testUpdateRating() {
        Rating existingRating = new Rating();
        when(ratingRepository.findById(1L)).thenReturn(Optional.of(existingRating));
        when(ratingRepository.save(any(Rating.class))).thenReturn(existingRating);
        when(modelMapper.map(any(Rating.class), eq(RatingDTO.class))).thenReturn(new RatingDTO());

        UpdateRatingInput update = new UpdateRatingInput();
        update.setRatings(4.0);

        RatingDTO result = ratingService.updateRating(1L, update);

        assertNotNull(result);
        assertEquals(4.0, existingRating.getRatings());
    }


    @Test
    void testGetAverageRating() {
        when(ratingRepository.findAverageGradeByToWhomUserId(1L)).thenReturn(4.5);

        Double result = ratingService.getAverageRating(1L);

        assertNotNull(result);
        assertEquals(4.5, result);
    }
    @Test
    void deleteRating() {
        doNothing().when(ratingRepository).deleteById(anyLong());
        ratingRepository.deleteById(1L);
        verify(ratingRepository, times(1)).deleteById(1L);
    }
}
