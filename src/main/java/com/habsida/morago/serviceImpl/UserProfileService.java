package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
import com.habsida.morago.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService implements com.habsida.morago.service.UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserProfileById(Long id) throws GlobalException {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new GlobalException("User Profile not found with id: " + id));
    }

    public UserProfile addUserProfile(Boolean isFreeCallMade, Long id) throws GlobalException {
        UserProfile userProfile = new UserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
        if (user.getUserProfile() == null) {
            user.setUserProfile(userProfile);
            userRepository.save(user);
        } else {
            throw new GlobalException("User already has a Profile attached");
        }
        return user.getUserProfile();
    }

    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws GlobalException {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new GlobalException("UserProfile not found with id: " + id));
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Long id) throws GlobalException {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new GlobalException("UserProfile not found with id: " + id));
        if (userProfile.getUser() != null){
            User user = userProfile.getUser();
            user.setUserProfile(null);
            userProfile.setUser(null);
            userRepository.save(user);
        }
        userProfileRepository.deleteById(id);
    }

    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws GlobalException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }
    public UserProfile changeIsFreeCallMade(Long id, Boolean isFreeCallMade) throws GlobalException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }
    public User changeBalanceByUserProfileId(Long id, Double balance) throws GlobalException {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new GlobalException("UserProfile not found with id: " + id));
        User user = userRepository.findById(userProfile.getUser().getId())
                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
        user.setBalance(balance);
        return userRepository.save(user);
    }
}