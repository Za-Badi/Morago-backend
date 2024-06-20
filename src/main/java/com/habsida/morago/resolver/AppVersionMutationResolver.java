package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.serviceImpl.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppVersionMutationResolver implements GraphQLMutationResolver {
    private final AppVersionService appVersionService;

    public AppVersion createAppVersion(EPlatform platform, String min, String latest) throws ExceptionGraphql {
        return appVersionService.createAppVersion(platform, min, latest);
    }

    public AppVersion updateAppVersion(EPlatform platform, String min, String latest) {
        return appVersionService.updateAppVersion(platform, min, latest);
    }

    public Boolean deleteAppVersion(EPlatform platform) {
        return appVersionService.delete(platform);
    }

}
