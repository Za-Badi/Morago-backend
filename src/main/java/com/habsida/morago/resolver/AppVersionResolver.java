package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.service.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AppVersionResolver implements GraphQLMutationResolver {
    private final AppVersionService appVersionService;
    public AppVersion createAppVersion(EPlatform platform, String min, String latest) {
        System.out.println("zaha za");
        return appVersionService.create(platform, min, latest);
    }
    public AppVersion updateAppVersion(EPlatform platform, String min, String latest) {
        return appVersionService.update(platform, min, latest);
    }
    public Boolean deleteAppVersion(EPlatform platform) {
        return appVersionService.delete(platform);
    }



}
