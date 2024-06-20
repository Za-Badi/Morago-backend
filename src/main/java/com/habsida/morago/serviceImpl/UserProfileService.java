package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
import com.habsida.morago.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService  {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }
    @Transactional(readOnly = true)
    public UserProfile getUserProfileById(Long id) throws ExceptionGraphql {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User Profile not found with id: " + id));
    }
    @Transactional
    public UserProfile addUserProfile(Boolean isFreeCallMade, Long id) throws ExceptionGraphql {
        UserProfile userProfile = new UserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        if (user.getUserProfile() == null) {
            user.setUserProfile(userProfile);
            userRepository.save(user);
        } else {
            throw new ExceptionGraphql("User already has a Profile attached");
        }
        return user.getUserProfile();
    }
    @Transactional
    public UserProfile updateUserProfile(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("UserProfile not found with id: " + id));
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }
    @Transactional
    public void deleteUserProfile(Long id) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("UserProfile not found with id: " + id));
        if (userProfile.getUser() != null){
            User user = userProfile.getUser();
            user.setUserProfile(null);
            userProfile.setUser(null);
            userRepository.save(user);
        }
        userProfileRepository.deleteById(id);
    }
    @Transactional
    public UserProfile updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        return userProfileRepository.save(userProfile);
    }
    @Transactional
    public Boolean changeIsFreeCallMade(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        userProfileRepository.save(userProfile);
        return true;
    }
    @Transactional
    public User changeBalanceByUserProfileId(Long id, Double balance) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setBalance(balance);
        return userRepository.save(user);
    }
}