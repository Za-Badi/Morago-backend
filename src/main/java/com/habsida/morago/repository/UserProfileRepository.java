package com.habsida.morago.repository;

import com.habsida.morago.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Modifying
    @Query("UPDATE UserProfile up SET up.isFreeCallMade = :isFreeCallMade WHERE up.user.id = :userId")
    int updateIsFreeCallMadeByUserId(@Param("userId") Long userId, @Param("isFreeCallMade") Boolean isFreeCallMade);

}
