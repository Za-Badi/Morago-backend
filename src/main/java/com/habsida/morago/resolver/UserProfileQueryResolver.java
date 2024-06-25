package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserProfileDTO;
import com.habsida.morago.serviceImpl.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProfileQueryResolver implements GraphQLQueryResolver {
    private final UserProfileService userProfileService;

    public List<UserProfileDTO> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    public UserProfileDTO getUserProfileById(Long id) throws ExceptionGraphql {
        return userProfileService.getUserProfileById(id);
    }
}
