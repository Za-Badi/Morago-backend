package com.habsida.morago.service;

import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;

import java.util.List;

public interface TranslatorProfileService {
    public List<TranslatorProfile> getAllTranslatorProfiles();
    public TranslatorProfile getTranslatorProfileById(Long id) throws Exception;
    public List<TranslatorProfile> getTranslatorProfilesByIsOnline(Boolean isOnline);
    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileDto) throws Exception;
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws Exception;
    public void deleteTranslatorProfile(Long id) throws Exception;

}
