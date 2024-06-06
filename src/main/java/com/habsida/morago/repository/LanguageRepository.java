package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findById(Long id);
}
