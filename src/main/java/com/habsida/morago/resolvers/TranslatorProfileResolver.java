package com.habsida.morago.resolvers;

import com.habsida.morago.dtos.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.services.TranslatorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TranslatorProfileResolver {
    private final TranslatorProfileService translatorProfileService;
    @Autowired
    public TranslatorProfileResolver(TranslatorProfileService translatorProfileService) {this.translatorProfileService = translatorProfileService;}
    public List<TranslatorProfile> getAllTranslatorProfiles() {
        return translatorProfileService.getAllTranslatorProfiles();
    }
    public TranslatorProfile getTranslatorProfileById(Long id) throws Exception {
        return translatorProfileService.getTranslatorProfileById(id);
    }
    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileDto) {
        return translatorProfileService.addTranslatorProfile(translatorProfileDto);
    }
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws Exception {
        return translatorProfileService.updateTranslatorProfile(id, translatorProfileDto);
    }
    public Boolean deleteTranslatorProfile(Long id) throws Exception {
        translatorProfileService.deleteTranslatorProfile(id);
        return true;
    }
}
