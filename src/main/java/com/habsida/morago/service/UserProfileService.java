package com.habsida.morago.service;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    public List<UserProfile> getAllUserProfiles();

    public UserProfile getUserProfileById(Long id) throws Exception;

    public UserProfile addUserProfile(Boolean isFreeCallMade);

    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws Exception;
    public Boolean changeIsFreeCallMade(Long id) throws Exception;
    public void deleteUserProfile(Long id) throws Exception;
    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws Exception;
    public User changeBalanceByUserProfileId(Long id, Float balance) throws Exception;
}