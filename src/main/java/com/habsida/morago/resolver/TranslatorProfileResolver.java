package com.habsida.morago.resolver;

import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.service.TranslatorProfileService;
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
    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileInput) throws Exception {
        return translatorProfileService.addTranslatorProfile(translatorProfileInput);
    }
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws Exception {
        return translatorProfileService.updateTranslatorProfile(id, translatorProfileInput);
    }
    public Boolean deleteTranslatorProfile(Long id) throws Exception {
        translatorProfileService.deleteTranslatorProfile(id);
        return true;
    }
}