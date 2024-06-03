package com.habsida.morago.resolver;


import com.habsida.morago.exceptions.GraphqlExceptionHandler;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.serviceImpl.AppVersionService;
import lombok.RequiredArgsConstructor;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AppVersionResolver {
    private final AppVersionService appVersionService;

    @MutationMapping
    public AppVersion createAppVersion(@Argument EPlatform platform, @Argument  String min, @Argument  String latest) throws GraphqlExceptionHandler {
        return appVersionService.create(platform, min, latest);
    }
    @MutationMapping
    public AppVersion updateAppVersion(@Argument EPlatform platform,@Argument String min,@Argument String latest) {
        return appVersionService.update(platform, min, latest);
    }
    @MutationMapping
    public Boolean deleteAppVersion(@Argument EPlatform platform) {
        return appVersionService.delete(platform);
    }

    @QueryMapping
    public AppVersion getAppVersionByPlatform(@Argument EPlatform platform) {
        return appVersionService.getByPlatform(platform);
    }
    @QueryMapping
    public List<AppVersion> getAllAppVersions() {
        return appVersionService.getAll();
    }


}
