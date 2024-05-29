package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public Optional<UserProfile> getUserProfileById(Long id) throws Exception {
        return userProfileService.getUserProfileById(id);
    }
    public UserProfile addUserProfile(UserProfile userProfile) {
        return userProfileService.addUserProfile(userProfile);
    }
    public UserProfile updateUserProfile(Long id, UserProfile userProfileUpdate) throws Exception {
        return userProfileService.updateUserProfile(id, userProfileUpdate);
    }
    public Boolean deleteUserProfile(Long id) throws Exception {
        userProfileService.deleteUserProfile(id);
        return true;
    }
}
