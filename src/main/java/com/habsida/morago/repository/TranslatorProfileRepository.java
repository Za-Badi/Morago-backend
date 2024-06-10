package com.habsida.morago.repository;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.TranslatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TranslatorProfileRepository extends JpaRepository<TranslatorProfile, Long> {
}
