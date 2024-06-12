package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.service.TranslatorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
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
    public List<TranslatorProfile> getTranslatorProfilesByIsOnline(Boolean isOnline) {
        return translatorProfileService.getTranslatorProfilesByIsOnline(isOnline);
    }
    public List<TranslatorProfile> getTranslatorProfilesByThemeId(Long id) throws Exception {
        return translatorProfileService.getTranslatorProfilesByThemeId(id);
    }
    public List<TranslatorProfile> getTranslatorProfilesByThemeName(String name) throws Exception {
        return translatorProfileService.getTranslatorProfilesByThemeName(name);
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
    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws Exception {
        return translatorProfileService.updateTranslatorProfileByUserId(id, translatorProfileInput);
    }
    public Boolean changeIsAvailable(Long id) throws Exception {
        return translatorProfileService.changeIsAvailable(id);
    }
    public Boolean changeIsOnline(Long id) throws Exception {
        return translatorProfileService.changeIsOnline(id);
    }
    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws Exception {
        return translatorProfileService.addLanguageToTranslatorProfile(languageId, translatorProfileId);
    }
    public Boolean deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws Exception {
        translatorProfileService.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
        return true;
    }
    public User changeBalanceForTranslatorProfileId(Long id, Float balance) throws Exception {
        return translatorProfileService.changeBalanceForTranslatorProfileId(id, balance);
    }
}