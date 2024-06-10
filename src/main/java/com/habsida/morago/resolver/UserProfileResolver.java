package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileResolver {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileResolver(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    public UserProfile getUserProfileById(Long id) throws Exception {
        return userProfileService.getUserProfileById(id);
    }

    public UserProfile addUserProfile(Boolean isFreeCallMade) {
        return userProfileService.addUserProfile(isFreeCallMade);
    }

    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws Exception {
        return userProfileService.updateUserProfile(id, isFreeCallMade);
    }

    public Boolean deleteUserProfile(Long id) throws Exception {
        userProfileService.deleteUserProfile(id);
        return true;
    }
}