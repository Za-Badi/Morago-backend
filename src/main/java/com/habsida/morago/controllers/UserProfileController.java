package com.habsida.morago.controllers;

import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.resolver.UserProfileResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserProfileController {
    private final UserProfileResolver userProfileResolver;

    public UserProfileController(UserProfileResolver userProfileResolver) {
        this.userProfileResolver = userProfileResolver;
    }

    @QueryMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileResolver.getAllUserProfiles();
    }

    @QueryMapping
    public UserProfile getUserProfileById(@Argument Long id) throws Exception {
        return userProfileResolver.getUserProfileById(id);
    }

    @MutationMapping
    public UserProfile addUserProfile(@Argument Boolean isFreeCallMade) {
        return userProfileResolver.addUserProfile(isFreeCallMade);
    }

    @MutationMapping
    public UserProfile updateUserProfile(@Argument Long id, @Argument Boolean isFreeCallMade) throws Exception {
        return userProfileResolver.updateUserProfile(id, isFreeCallMade);
    }

    @MutationMapping
    public Boolean changeIsFreeCallMade(@Argument Long id) throws Exception {
        return userProfileResolver.changeIsFreeCallMade(id);
    }

    @MutationMapping
    public Boolean deleteUserProfile(@Argument Long id) throws Exception {
        return userProfileResolver.deleteUserProfile(id);
    }

    @MutationMapping
    public UserProfile updateUserProfileByUserId(@Argument Long id, @Argument Boolean isFreeCallMade) throws Exception {
        return userProfileResolver.updateUserProfileByUserId(id, isFreeCallMade);
    }

    @MutationMapping
    public User changeBalanceByUserProfileId(@Argument Long id, @Argument Float balance) throws Exception {
        return userProfileResolver.changeBalanceByUserProfileId(id, balance);
    }
}
