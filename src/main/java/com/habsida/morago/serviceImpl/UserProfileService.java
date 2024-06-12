package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService implements com.habsida.morago.service.UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
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
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("UserProfile not found with id: " + id));
        if (userProfile.getUser() != null){
            User user = userProfile.getUser();
            user.setUserProfile(null);
            userProfile.setUser(null);
            userRepository.save(user);
        }
        userProfileRepository.deleteById(id);
    }

    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }
    public Boolean changeIsFreeCallMade(Long id) throws Exception {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("UserProfile not found with id: " + id));
        userProfile.setIsFreeCallMade(!userProfile.getIsFreeCallMade());
        return true;
    }
    public User changeBalanceByUserProfileId(Long id, Float balance) throws Exception {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("UserProfile not found with id: " + id));
        User user = userRepository.findById(userProfile.getUser().getId())
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        Double newBalance = balance.doubleValue();
        user.setBalance(newBalance);
        return userRepository.save(user);
    }
}