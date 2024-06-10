package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService implements com.habsida.morago.service.UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserProfileById(Long id) throws Exception {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("User Profile not found with id: " + id));
    }

    public UserProfile addUserProfile(Boolean isFreeCallMade) {
        UserProfile userProfile = new UserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws Exception {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("UserProfile not found with id: " + id));
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Long id) throws Exception {
        userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("UserProfile not found with id: " + id));
        userProfileRepository.deleteById(id);
    }
}