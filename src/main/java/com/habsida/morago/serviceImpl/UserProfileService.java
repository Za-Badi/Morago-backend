package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.dto.UserProfileDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserProfileRepository;
import com.habsida.morago.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<UserProfileDTO> getAllUserProfiles() {
        return userProfileRepository.findAll().stream()
                .map(userProfile -> modelMapper.map(userProfile, UserProfileDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfileById(Long id) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User Profile not found with id: " + id));
        return modelMapper.map(userProfile, UserProfileDTO.class);
    }

    @Transactional
    public UserProfileDTO addUserProfile(Boolean isFreeCallMade, Long id) throws ExceptionGraphql {
        UserProfile userProfile = new UserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        if (user.getTranslatorProfile() != null) {
            throw new ExceptionGraphql("User has a Translator Profile attached");
        }
        if (user.getUserProfile() == null) {
            user.setUserProfile(userProfile);
            userRepository.save(user);
        } else {
            throw new ExceptionGraphql("User already has a User Profile attached");
        }
        return modelMapper.map(user.getUserProfile(), UserProfileDTO.class);
    }

    @Transactional
    public UserProfileDTO updateUserProfile(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("UserProfile not found with id: " + id));
        userProfile.setIsFreeCallMade(isFreeCallMade);
        UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
        return modelMapper.map(updatedUserProfile, UserProfileDTO.class);
    }

    @Transactional
    public void deleteUserProfile(Long id) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("UserProfile not found with id: " + id));
        if (userProfile.getUser() != null) {
            User user = userProfile.getUser();
            user.setUserProfile(null);
            userProfile.setUser(null);
            userRepository.save(user);
        }
        userProfileRepository.deleteById(id);
    }

    @Transactional
    public UserProfileDTO updateUserProfileByUserId(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        if (user.getUserProfile() == null) {
            throw new ExceptionGraphql("User doesn't hava a User Profile attached");
        }
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
        return modelMapper.map(updatedUserProfile, UserProfileDTO.class);
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
    public UserDTO changeBalanceByUserProfileId(Long id, Double balance) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        user.setBalance(balance);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}
