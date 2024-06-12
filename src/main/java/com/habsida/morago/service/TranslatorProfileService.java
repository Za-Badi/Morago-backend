package com.habsida.morago.service;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;

import java.util.List;

public interface TranslatorProfileService {
    public List<TranslatorProfile> getAllTranslatorProfiles();
    public TranslatorProfile getTranslatorProfileById(Long id) throws Exception;
    public List<TranslatorProfile> getTranslatorProfilesByIsOnline(Boolean isOnline);
    public List<TranslatorProfile> getTranslatorProfilesByThemeId(Long id) throws Exception;
    public List<TranslatorProfile> getTranslatorProfilesByThemeName(String name) throws Exception;
    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileDto) throws Exception;
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws Exception;
    public void deleteTranslatorProfile(Long id) throws Exception;
    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws Exception;
    public boolean changeIsAvailable(Long id) throws Exception;
    public boolean changeIsOnline(Long id) throws Exception;
    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws Exception;
    public void deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws Exception;
    public User changeBalanceForTranslatorProfileId(Long id, Float balance) throws Exception;
}
