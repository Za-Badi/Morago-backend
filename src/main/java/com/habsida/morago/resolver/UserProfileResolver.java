package com.habsida.morago.resolver;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProfileResolver {
    private final UserProfileService userProfileService;

    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    public UserProfile getUserProfileById(Long id) throws GlobalException {
        return userProfileService.getUserProfileById(id);
    }

    public UserProfile addUserProfile(Boolean isFreeCallMade, Long id) throws GlobalException {
        return userProfileService.addUserProfile(isFreeCallMade, id);
    }

    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws GlobalException {
        return userProfileService.updateUserProfile(id, isFreeCallMade);
    }

    public UserProfile changeIsFreeCallMade(Long id, Boolean isFreeCallMade) throws GlobalException {
        return userProfileService.changeIsFreeCallMade(id, isFreeCallMade);
    }

    public Boolean deleteUserProfile(Long id) throws GlobalException {
        userProfileService.deleteUserProfile(id);
        return true;
    }

    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws GlobalException {
        return userProfileService.updateUserProfileByUserId(id, isFreeCallMade);
    }
    public User changeBalanceByUserProfileId(Long id, Double balance) throws GlobalException {
        return userProfileService.changeBalanceByUserProfileId(id, balance);
    }
}