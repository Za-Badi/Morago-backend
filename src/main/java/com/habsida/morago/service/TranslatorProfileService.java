package com.habsida.morago.service;

import com.habsida.morago.model.entity.TranslatorProfile;

import java.util.List;
import java.util.Optional;

public interface TranslatorProfileService {
    public List<TranslatorProfile> getAllTranslatorProfiles();
    public Optional<TranslatorProfile> getTranslatorProfileById(Long id) throws Exception;
    public TranslatorProfile addTranslatorProfile(TranslatorProfile translatorProfile);
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfile translatorProfileUpdate) throws Exception;
    public void deleteTranslatorProfile(Long id) throws Exception;
}
