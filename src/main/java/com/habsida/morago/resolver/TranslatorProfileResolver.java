package com.habsida.morago.resolver;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorPage;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.service.TranslatorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TranslatorProfileResolver {
    private final TranslatorProfileService translatorProfileService;
    public List<TranslatorProfile> getAllTranslatorProfiles() {
        return translatorProfileService.getAllTranslatorProfiles();
    }
    public TranslatorPage getAllTranslatorProfilesPaged(Integer page, Integer size) {
        return translatorProfileService.getAllTranslatorProfilesPaged(page, size);
    }
    public TranslatorProfile getTranslatorProfileById(Long id) throws GlobalException {
        return translatorProfileService.getTranslatorProfileById(id);
    }
    public List<TranslatorProfile> getTranslatorProfilesByIsOnlineAndThemeId(Boolean isOnline, Long id) {
        return translatorProfileService.getTranslatorProfilesByIsOnlineAndThemeId(isOnline, id);
    }
    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(Integer page, Integer size, Long id, Boolean isOnline) {
        return translatorProfileService.getTranslatorProfilesByIsOnlineAndThemeIdPaged(page, size, id, isOnline);
    }
    public List<TranslatorProfile> getTranslatorProfilesByThemeId(Long id)  {
        return translatorProfileService.getTranslatorProfilesByThemeId(id);
    }
    public TranslatorPage getTranslatorProfilesByThemeIdPaged(Integer page, Integer size, Long id) {
        return translatorProfileService.getTranslatorProfilesByThemeIdPaged(page, size, id);
    }
    public List<TranslatorProfile> getTranslatorProfilesByThemeName(String name)  {
        return translatorProfileService.getTranslatorProfilesByThemeName(name);
    }
    public TranslatorPage getTranslatorProfilesByThemeNamePaged(Integer page, Integer size, String name) {
        return translatorProfileService.getTranslatorProfilesByThemeNamePaged(page, size, name);
    }
    public TranslatorProfile addTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException {
        return translatorProfileService.addTranslatorProfile(id, translatorProfileInput);
    }
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException {
        return translatorProfileService.updateTranslatorProfile(id, translatorProfileInput);
    }
    public Boolean deleteTranslatorProfile(Long id) throws GlobalException {
        translatorProfileService.deleteTranslatorProfile(id);
        return true;
    }
    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException {
        return translatorProfileService.updateTranslatorProfileByUserId(id, translatorProfileInput);
    }
    public TranslatorProfile changeIsAvailable(Long id, Boolean isAvailable) throws GlobalException {
        return translatorProfileService.changeIsAvailable(id, isAvailable);
    }
    public TranslatorProfile changeIsOnline(Long id, Boolean isOnline) throws GlobalException {
        return translatorProfileService.changeIsOnline(id, isOnline);
    }
    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws GlobalException {
        return translatorProfileService.addLanguageToTranslatorProfile(languageId, translatorProfileId);
    }
    public Boolean deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws GlobalException {
        translatorProfileService.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
        return true;
    }
    public User changeBalanceForTranslatorProfileId(Long id, Float balance) throws GlobalException {
        return translatorProfileService.changeBalanceForTranslatorProfileId(id, balance);
    }
}