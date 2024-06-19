package com.habsida.morago.repository;
import com.habsida.morago.model.entity.TranslatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslatorProfileRepository extends JpaRepository<TranslatorProfile, Long> {
    List<TranslatorProfile> findByThemesId(Long themeId);
    List<TranslatorProfile> findByIsOnlineAndThemesId(Boolean isOnline, Long themeId);
    List<TranslatorProfile> findByThemesName(String name);
}