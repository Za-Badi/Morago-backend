package com.habsida.morago.repository;

import com.habsida.morago.model.entity.ConsultantProfile;
import com.habsida.morago.model.entity.TranslatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ConsultantProfileRepository extends JpaRepository<ConsultantProfile, Long> {
    List<ConsultantProfile> findByLanguagesId(Long languageId);
    List<ConsultantProfile> findByIsOnlineAndLanguagesId(Boolean isOnline, Long languageId);
    List<ConsultantProfile> findByLanguagesName(String name);
    Optional<ConsultantProfile> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE ConsultantProfile c SET c.isAvailable = :isAvailable WHERE c.id = :id")
    int updateIsAvailableById(Long id, Boolean isAvailable);

    @Modifying
    @Transactional
    @Query("UPDATE ConsultantProfile c SET c.isOnline = :isOnline WHERE c.id = :id")
    int updateIsOnlineById(Long id, Boolean isOnline);
}
