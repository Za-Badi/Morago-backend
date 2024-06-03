package com.habsida.morago.resolver;

import com.habsida.morago.dtos.UserInput;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.service.UserProfileService;
import com.habsida.morago.serviceImpl.UserService;
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
}