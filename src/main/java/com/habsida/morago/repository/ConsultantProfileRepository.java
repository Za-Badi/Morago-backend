package com.habsida.morago.repository;

import com.habsida.morago.model.entity.ConsultantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultantProfileRepository extends JpaRepository<ConsultantProfile, Long> {
    List<ConsultantProfile> findByLanguagesId(Long languageId);
    List<ConsultantProfile> findByIsOnlineAndLanguagesId(Boolean isOnline, Long languageId);
    List<ConsultantProfile> findByLanguagesName(String name);
}
