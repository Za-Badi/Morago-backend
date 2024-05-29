package com.habsida.morago.resolver;

//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import graphql.kickstart.tools.GraphQLMutationResolver;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
import com.habsida.morago.exceptions.GraphqlExceptionHandler;
import com.habsida.morago.model.entity.AppVersion;
import com.habsida.morago.model.enums.EPlatform;
import com.habsida.morago.service.AppVersionService;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


//@Component
@Controller

@RequiredArgsConstructor
public class AppVersionResolver {
    private final AppVersionService appVersionService;

    @MutationMapping
    public AppVersion createAppVersion(@Argument EPlatform platform,@Argument  String min,@Argument  String latest) throws GraphqlExceptionHandler {
        System.out.println("zaha za");
        return appVersionService.create(platform, min, latest);
    }
    @MutationMapping
    public AppVersion updateAppVersion(EPlatform platform, String min, String latest) {
        return appVersionService.update(platform, min, latest);
    }
    @MutationMapping
    public Boolean deleteAppVersion(EPlatform platform) {
        return appVersionService.delete(platform);
    }



}
