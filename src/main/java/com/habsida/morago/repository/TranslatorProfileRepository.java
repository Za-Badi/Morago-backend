package com.habsida.morago.repository;
import com.habsida.morago.model.entity.TranslatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TranslatorProfileRepository extends JpaRepository<TranslatorProfile, Long> {
    List<TranslatorProfile> findByThemesId(Long themeId);
    List<TranslatorProfile> findByIsOnlineAndThemesId(Boolean isOnline, Long themeId);
    List<TranslatorProfile> findByThemesName(String name);

    @Modifying
    @Query("UPDATE TranslatorProfile t SET t.isAvailable = :isAvailable WHERE t.id = :id")
    int updateIsAvailableById(@Param("id") Long id, @Param("isAvailable") Boolean isAvailable);

    @Modifying
    @Query("UPDATE TranslatorProfile t SET t.isOnline = :isOnline WHERE t.id = :id")
    int updateIsOnlineById(@Param("id") Long id, @Param("isOnline") Boolean isOnline);
}