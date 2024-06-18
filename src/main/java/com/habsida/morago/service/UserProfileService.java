package com.habsida.morago.service;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    public List<UserProfile> getAllUserProfiles();

    public UserProfile getUserProfileById(Long id) throws GlobalException;

    public UserProfile addUserProfile(Boolean isFreeCallMade, Long id) throws GlobalException;

    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws GlobalException;
    public UserProfile changeIsFreeCallMade(Long id, Boolean isFreeCallMade) throws GlobalException;
    public void deleteUserProfile(Long id) throws GlobalException;
    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws GlobalException;
    public User changeBalanceByUserProfileId(Long id, Double balance) throws GlobalException;
}