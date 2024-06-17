package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppVersionService {
    private final AppVersionRepository repository;

    public AppVersion createAppVersion(EPlatform platform, String min, String latest)  {
        AppVersion appVersion = new AppVersion();
        appVersion.setPlatform(platform);
        if( min !=null && !min.isBlank()){
            appVersion.setMin(min);
        }
        else {
            throw new  NullPointerException("minimum version is required");
        }
        if((latest != null) && !latest.isBlank()){
            appVersion.setLatest(latest);
        }
        else {
            throw new NullPointerException("latest version is required");
        }
        return repository.save(appVersion);
    }

    public AppVersion getAppVersionByPlatform(EPlatform platform) {
        return repository.findByPlatform(platform).orElseThrow(()-> new EntityNotFoundException("No App Version with name: "+ platform));
    }

    public AppVersion updateAppVersion(EPlatform platform, String min, String latest) {
        AppVersion appVersion = getAppVersionByPlatform(platform);
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
        AppVersion appVersion = getAppVersionByPlatform(platform);
        repository.delete(appVersion);
        return true;
    }
}
