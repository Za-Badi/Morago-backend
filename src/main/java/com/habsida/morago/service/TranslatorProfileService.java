package com.habsida.morago.service;

import com.habsida.morago.exceptions.GlobalException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorPage;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;

import java.util.List;

public interface TranslatorProfileService {
    public List<TranslatorProfile> getAllTranslatorProfiles();
    public TranslatorPage getAllTranslatorProfilesPaged(Integer page, Integer size);
    public TranslatorProfile getTranslatorProfileById(Long id) throws GlobalException;
    public List<TranslatorProfile> getTranslatorProfilesByIsOnlineAndThemeId(Boolean isOnline, Long id);
    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(Integer page, Integer size, Long id, Boolean isOnline);
    public List<TranslatorProfile> getTranslatorProfilesByThemeId(Long id) ;
    public TranslatorPage getTranslatorProfilesByThemeIdPaged(Integer page, Integer size, Long id);
    public List<TranslatorProfile> getTranslatorProfilesByThemeName(String name);
    public TranslatorPage getTranslatorProfilesByThemeNamePaged(Integer page, Integer size, String name);
    public TranslatorProfile addTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws GlobalException;
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws GlobalException;
    public void deleteTranslatorProfile(Long id) throws GlobalException;
    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException;
    public TranslatorProfile changeIsAvailable(Long id, Boolean isAvailable) throws GlobalException;
    public TranslatorProfile changeIsOnline(Long id, Boolean isOnline) throws GlobalException;
    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws GlobalException;
    public void deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws GlobalException;
    public User changeBalanceForTranslatorProfileId(Long id, Float balance) throws GlobalException;
}
