package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT AVG(r.ratings) FROM Rating r WHERE r.toWhomUser.id = :userId")
    Double findAverageGradeByToWhomUserId(@Param("userId") Long userId);

    List<Rating> findByToWhomUserId(Long toWhomUserId);
}