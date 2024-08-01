package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.dto.UserProfileDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.UserProfilePageOutput;
import com.habsida.morago.repository.UserProfileRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public UserProfilePageOutput getAllUserProfiles(PagingInput pagingInput) {
        Page<UserProfile> page = userProfileRepository.findAll(PageUtil.buildPageable(pagingInput));
        return new UserProfilePageOutput(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.map(userProfile -> modelMapperUtil.map(userProfile, UserProfileDTO.class)).getContent()
        );
    }

    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfileById(Long id) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User Profile not found with id: " + id));
        return modelMapperUtil.map(userProfile, UserProfileDTO.class);
    }

    @Transactional
    public UserProfileDTO addUserProfile(Boolean isFreeCallMade, Long userId) throws ExceptionGraphql {
        UserProfile userProfile = new UserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + userId));
        if (user.getTranslatorProfile() != null) {
            throw new ExceptionGraphql("User has a Translator Profile attached");
        }
        if (user.getUserProfile() == null) {
            user.setUserProfile(userProfile);
            userRepository.save(user);
        } else {
            throw new ExceptionGraphql("User already has a User Profile attached");
        }
        return modelMapperUtil.map(user.getUserProfile(), UserProfileDTO.class);
    }

    @Transactional
    public UserProfileDTO updateUserProfile(Long id, Boolean isFreeCallMade) throws ExceptionGraphql {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("UserProfile not found with id: " + id));
        userProfile.setIsFreeCallMade(isFreeCallMade);
        UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
        return modelMapperUtil.map(updatedUserProfile, UserProfileDTO.class);
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
    public UserProfileDTO updateUserProfileByUserId(Long userId, Boolean isFreeCallMade) throws ExceptionGraphql {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + userId));
        if (user.getUserProfile() == null) {
            throw new ExceptionGraphql("User doesn't have a User Profile attached");
        }
        UserProfile userProfile = user.getUserProfile();
        userProfile.setIsFreeCallMade(isFreeCallMade);
        UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
        return modelMapperUtil.map(updatedUserProfile, UserProfileDTO.class);
    }

    @Transactional
    public Boolean changeIsFreeCallMade(Long userId, Boolean isFreeCallMade) throws ExceptionGraphql {
        int updated = userProfileRepository.updateIsFreeCallMadeByUserId(userId, isFreeCallMade);
        if (updated == 0) {
            throw new ExceptionGraphql("UserProfile not found or update failed for user id: " + userId);
        }
        return true;
    }

    @Transactional
    public UserDTO changeBalanceByUserProfileId(Long userId, Double balance) throws ExceptionGraphql {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + userId));
        user.setBalance(balance);
        User updatedUser = userRepository.save(user);
        return modelMapperUtil.map(updatedUser, UserDTO.class);
    }
}
