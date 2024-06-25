package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.dto.UserProfileDTO;
import com.habsida.morago.serviceImpl.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {
    private final UserProfileService userProfileService;

    public UserProfileDTO addUserProfile(Boolean isFreeCallMade, Long id) throws ExceptionGraphql {
        return userProfileService.addUserProfile(isFreeCallMade, id);
    }

    public UserProfileDTO updateUserProfile(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        return userProfileService.updateUserProfile(id, isFreeCallMade);
    }

    public Boolean changeIsFreeCallMade(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        return userProfileService.changeIsFreeCallMade(id, isFreeCallMade);
    }

    public Boolean deleteUserProfile(Long id) throws ExceptionGraphql {
        userProfileService.deleteUserProfile(id);
        return true;
    }

    public UserProfileDTO updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        return userProfileService.updateUserProfileByUserId(id, isFreeCallMade);
    }

    public UserDTO changeBalanceByUserProfileId(Long id, Double balance) throws ExceptionGraphql {
        return userProfileService.changeBalanceByUserProfileId(id, balance);
    }
}
