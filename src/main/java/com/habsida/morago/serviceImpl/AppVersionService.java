package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.AppVersionDTO;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.model.enums.ESort;
import com.habsida.morago.model.inputs.PagingInputAppVersion;
import com.habsida.morago.model.results.AppVersionPageOutput;
import com.habsida.morago.repository.AppVersionRepository;
import com.habsida.morago.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppVersionService {
    private final AppVersionRepository repository;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public AppVersionDTO getAppVersionByPlatform(EPlatform platform) {
        AppVersion appVersion = repository.findByPlatform(platform)
                .orElseThrow(() -> new EntityNotFoundException("No App Version with platform: " + platform));
        return modelMapperUtil.map(appVersion, AppVersionDTO.class);
    }

    @Transactional(readOnly = true)
    public AppVersionPageOutput getAll(PagingInputAppVersion pagingInput) {
        Sort sort = pagingInput.getSort().equals(ESort.DES) ?
                Sort.by(pagingInput.getSortBy()).ascending() :
                Sort.by(pagingInput.getSortBy()).descending();
        Pageable pageable = PageRequest.of(pagingInput.getPageNo(), pagingInput.getPageSize(), sort);

        Page<AppVersion> page = repository.findAll(pageable);
        return new AppVersionPageOutput(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.stream().map(appVersion -> modelMapperUtil.map(appVersion, AppVersionDTO.class)).collect(Collectors.toList())
        );
    }

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
        return modelMapperUtil.map(savedAppVersion, AppVersionDTO.class);
    }

    @Transactional
    public AppVersionDTO updateAppVersion(EPlatform platform, String min, String latest) {
        AppVersion appVersion = repository.findByPlatform(platform)
                .orElseThrow(() -> new EntityNotFoundException("No App Version with platform: " + platform));
        if (min != null && !min.isBlank()) {
            appVersion.setMin(min);
        }
        if (latest != null && !latest.isBlank()) {
            appVersion.setLatest(latest);
        }
        AppVersion updatedAppVersion = repository.save(appVersion);
        return modelMapperUtil.map(updatedAppVersion, AppVersionDTO.class);
    }

    @Transactional
    public Boolean delete(EPlatform platform) {
        AppVersion appVersion = repository.findByPlatform(platform)
                .orElseThrow(() -> new EntityNotFoundException("No App Version with platform: " + platform));
        repository.delete(appVersion);
        return true;
    }
}
