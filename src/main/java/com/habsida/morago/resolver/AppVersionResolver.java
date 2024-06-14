package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.serviceImpl.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AppVersionResolver {
    private final AppVersionService appVersionService;

    public AppVersion createAppVersion(EPlatform platform, String min, String latest) {
        return appVersionService.createAppVersion(platform, min, latest);
    }

    public AppVersion updateAppVersion(EPlatform platform, String min, String latest) {
        return appVersionService.updateAppVersion(platform, min, latest);
    }

    public Boolean deleteAppVersion(EPlatform platform) {
        return appVersionService.delete(platform);
    }

    public AppVersion getAppVersionByPlatform(EPlatform platform) {
        return appVersionService.getAppVersionByPlatform(platform);
    }

    public List<AppVersion> getAllAppVersions() {
        return appVersionService.getAll();
    }


}
