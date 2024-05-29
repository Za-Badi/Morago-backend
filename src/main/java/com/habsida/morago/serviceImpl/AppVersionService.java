package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.repository.AppVersionRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppVersionService {
    private final AppVersionRespository repository;

    public AppVersion create(EPlatform platform, String min, String latest) {
        AppVersion appVersion = new AppVersion();
        appVersion.setPlatform(platform);
        appVersion.setMin(min);
        appVersion.setLatest(latest);
        return repository.save(appVersion);
    }

    public AppVersion getByPlatform(EPlatform platform) {
        return repository.findByPlatform(platform).orElseThrow(EntityNotFoundException::new);
    }

    public AppVersion update(EPlatform platform, String min, String latest) {
        AppVersion appVersion = getByPlatform(platform);
        if (min != null && !min.isEmpty()) {
            appVersion.setMin(min);
        }
        if (latest != null && !latest.isEmpty()) {
            appVersion.setLatest(latest);
        }
        return repository.save(appVersion);
    }

    public List<AppVersion> getAll() {
        return repository.findAll();
    }

    public Boolean delete(EPlatform platform) {
        AppVersion appVersion = getByPlatform(platform);
        repository.delete(appVersion);
        return true;
    }
}
