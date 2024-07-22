package com.habsida.morago.repository;

import com.habsida.morago.model.entity.ConsultantProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConsultantProfileRepositoryPaged extends PagingAndSortingRepository<ConsultantProfile, Long> {
    Page<ConsultantProfile> findByLanguagesName(String name, Pageable pageable);
    Page<ConsultantProfile> findByIsOnlineAndLanguagesId(Boolean isOnline, Long id, Pageable pageable);
    Page<ConsultantProfile> findByLanguagesId(Long id, Pageable pageable);
    @Query("SELECT DISTINCT c FROM ConsultantProfile c LEFT JOIN c.languages l WHERE " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(c.dateOfBirth) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(l.name) LIKE LOWER(CONCAT('%', :searchInput, '%'))")
    Page<ConsultantProfile> searchConsultantProfileByEmailOrDateOfBirthOrLanguages(
            String searchInput,
            Pageable pageable
    );
}
