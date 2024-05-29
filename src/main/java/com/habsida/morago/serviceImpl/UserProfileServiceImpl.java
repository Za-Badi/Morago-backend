package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
import com.habsida.morago.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserProfileById(Long id) throws Exception {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            return userProfileRepository.findById(id);
        } else {
            throw new Exception("User Profile not found for id: " + id);
        }
    }

    public UserProfile addUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(Long id, UserProfile userProfileUpdate) throws Exception {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            userProfile.setIsFreeCallMade(userProfileUpdate.getIsFreeCallMade());
            return userProfileRepository.save(userProfile);
        } else {
            throw new Exception("User Profile not found for id: " + id);
        }
    }

    public void deleteUserProfile(Long id) throws Exception {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent()) {
            userProfileRepository.deleteById(id);
        } else {
            throw new Exception("User Profile not found for id: " + id);
        }
    }
}
