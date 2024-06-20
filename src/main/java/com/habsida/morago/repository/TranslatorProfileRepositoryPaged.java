package com.habsida.morago.repository;
import com.habsida.morago.model.entity.TranslatorProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TranslatorProfileRepositoryPaged extends PagingAndSortingRepository<TranslatorProfile, Long> {
    Page<TranslatorProfile> findByThemesName(String name, Pageable pageable);
    Page<TranslatorProfile> findByIsOnlineAndThemesId(Boolean isOnline, Long id, Pageable pageable);
    Page<TranslatorProfile> findByThemesId(Long id, Pageable pageable);
    @Query("SELECT DISTINCT t FROM TranslatorProfile t LEFT JOIN t.languages l WHERE " +
            "LOWER(t.email) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(t.levelOfKorean) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(t.dateOfBirth) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(l.name) LIKE LOWER(CONCAT('%', :searchInput, '%'))")
    Page<TranslatorProfile> searchTranslatorProfileByEmailOrLevelOfKoreanOrDateOfBirthOrLanguages(
            String searchInput,
            Pageable pageable
    );
}