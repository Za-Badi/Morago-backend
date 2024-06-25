package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.AppVersionDTO;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppVersionService {
    private final AppVersionRepository repository;
    private final ModelMapper modelMapper;

    public AppVersionDTO createAppVersion(EPlatform platform, String min, String latest) {
        AppVersion appVersion = new AppVersion();
        appVersion.setPlatform(platform);
        if (min != null && !min.isBlank()) {
            appVersion.setMin(min);
        } else {
            throw new NullPointerException("Minimum version is required");
        }
        if (latest != null && !latest.isBlank()) {
            appVersion.setLatest(latest);
        } else {
            throw new NullPointerException("Latest version is required");
        }
        AppVersion savedAppVersion = repository.save(appVersion);
        return modelMapper.map(savedAppVersion, AppVersionDTO.class);
    }

    public AppVersionDTO getAppVersionByPlatform(EPlatform platform) {
        AppVersion appVersion = repository.findByPlatform(platform)
                .orElseThrow(() -> new EntityNotFoundException("No App Version with platform: " + platform));
        return modelMapper.map(appVersion, AppVersionDTO.class);
    }

    public AppVersionDTO updateAppVersion(EPlatform platform, String min, String latest) {
        AppVersion appVersion = repository.findByPlatform(platform)
                .orElseThrow(() -> new EntityNotFoundException("No App Version with platform: " + platform));
        if (min != null && !min.isEmpty()) {
            appVersion.setMin(min);
        }
        if (latest != null && !latest.isEmpty()) {
            appVersion.setLatest(latest);
        }
        AppVersion updatedAppVersion = repository.save(appVersion);
        return modelMapper.map(updatedAppVersion, AppVersionDTO.class);
    }

    public List<AppVersionDTO> getAll() {
        return repository.findAll().stream()
                .map(appVersion -> modelMapper.map(appVersion, AppVersionDTO.class))
                .collect(Collectors.toList());
    }

    public Boolean delete(EPlatform platform) {
        AppVersion appVersion = repository.findByPlatform(platform)
                .orElseThrow(() -> new EntityNotFoundException("No App Version with platform: " + platform));
        repository.delete(appVersion);
        return true;
    }
}
