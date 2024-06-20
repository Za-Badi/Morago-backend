package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.serviceImpl.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {
    private final UserProfileService userProfileService;
    public UserProfile addUserProfile(Boolean isFreeCallMade, Long id) throws ExceptionGraphql {
        return userProfileService.addUserProfile(isFreeCallMade, id);
    }
    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        return userProfileService.updateUserProfile(id, isFreeCallMade);
    }
    public Boolean changeIsFreeCallMade(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        return userProfileService.changeIsFreeCallMade(id, isFreeCallMade);
    }
    public Boolean deleteUserProfile(Long id) throws ExceptionGraphql {
        userProfileService.deleteUserProfile(id);
        return true;
    }
    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        return userProfileService.updateUserProfileByUserId(id, isFreeCallMade);
    }
    public User changeBalanceByUserProfileId(Long id, Double balance) throws ExceptionGraphql {
        return userProfileService.changeBalanceByUserProfileId(id, balance);
    }
}
