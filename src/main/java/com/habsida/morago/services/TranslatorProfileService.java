package com.habsida.morago.services;

import com.habsida.morago.dtos.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;

import java.util.List;
import java.util.Optional;

public interface TranslatorProfileService {
    public List<TranslatorProfile> getAllTranslatorProfiles();
    public TranslatorProfile getTranslatorProfileById(Long id) throws Exception;
    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileDto) throws Exception;
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws Exception;
    public void deleteTranslatorProfile(Long id) throws Exception;
}
