package com.habsida.morago.controllers;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.resolver.UserProfileResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileResolver userProfileResolver;

    @QueryMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileResolver.getAllUserProfiles();
    }

    @QueryMapping
    public UserProfile getUserProfileById(@Argument Long id) throws GlobalException {
        return userProfileResolver.getUserProfileById(id);
    }

    @MutationMapping
    public UserProfile addUserProfile(@Argument Boolean isFreeCallMade, @Argument Long id) throws GlobalException {
        return userProfileResolver.addUserProfile(isFreeCallMade, id);
    }

    @MutationMapping
    public UserProfile updateUserProfile(@Argument Long id, @Argument Boolean isFreeCallMade) throws GlobalException {
        return userProfileResolver.updateUserProfile(id, isFreeCallMade);
    }

    @MutationMapping
    public UserProfile changeIsFreeCallMade(@Argument Long id, @Argument Boolean isFreeCallMade) throws GlobalException {
        return userProfileResolver.changeIsFreeCallMade(id, isFreeCallMade);
    }

    @MutationMapping
    public Boolean deleteUserProfile(@Argument Long id) throws GlobalException {
        return userProfileResolver.deleteUserProfile(id);
    }

    @MutationMapping
    public UserProfile updateUserProfileByUserId(@Argument Long id, @Argument Boolean isFreeCallMade) throws GlobalException {
        return userProfileResolver.updateUserProfileByUserId(id, isFreeCallMade);
    }

    @MutationMapping
    public User changeBalanceByUserProfileId(@Argument Long id, @Argument Double balance) throws GlobalException {
        return userProfileResolver.changeBalanceByUserProfileId(id, balance);
    }
}
