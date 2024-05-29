package com.habsida.morago.services;

import com.habsida.morago.model.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    public List<UserProfile> getAllUserProfiles();
    public Optional<UserProfile> getUserProfileById(Long id) throws Exception;
    public UserProfile addUserProfile(UserProfile userProfile);
    public UserProfile updateUserProfile(Long id, UserProfile userProfileUpdate) throws Exception;
    public void deleteUserProfile(Long id) throws Exception;
}
