package com.habsida.morago.service;

import com.habsida.morago.model.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    public List<UserProfile> getAllUserProfiles();
    public UserProfile getUserProfileById(Long id) throws Exception;
}