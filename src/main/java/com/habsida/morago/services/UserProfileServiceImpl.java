package com.habsida.morago.services;

import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
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

    public UserProfile getUserProfileById(Long id) throws Exception {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("User Profile not found with id: " + id));
    }
}
