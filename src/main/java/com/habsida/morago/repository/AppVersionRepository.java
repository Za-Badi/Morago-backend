package com.habsida.morago.repository;

import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.model.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    Optional<AppVersion> findByPlatform(EPlatform platform);
}
