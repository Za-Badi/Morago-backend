package com.habsida.morago.resolvers;

import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.services.TranslatorProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TranslatorProfileResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final TranslatorProfileService translatorProfileService;
    @Autowired
    public TranslatorProfileResolver(TranslatorProfileService translatorProfileService) {
        this.translatorProfileService = translatorProfileService;
    }
    public List<TranslatorProfile> getAllTranslatorProfiles() {
        return translatorProfileService.getAllTranslatorProfiles();
    }
    public Optional<TranslatorProfile> getTranslatorProfileById(Long id) throws Exception {
        return translatorProfileService.getTranslatorProfileById(id);
    }
    public TranslatorProfile addTranslatorProfile(TranslatorProfile translatorProfile) {
        return translatorProfileService.addTranslatorProfile(translatorProfile);
    }
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfile translatorProfileUpdate) throws Exception {
        return translatorProfileService.updateTranslatorProfile(id, translatorProfileUpdate);
    }
    public Boolean deleteTranslatorProfile(Long id) throws Exception {
        translatorProfileService.deleteTranslatorProfile(id);
        return true;
    }
}
