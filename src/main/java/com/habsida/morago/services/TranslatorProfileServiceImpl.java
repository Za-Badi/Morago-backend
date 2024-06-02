package com.habsida.morago.services;

import com.habsida.morago.dtos.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.repository.TranslatorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorProfileServiceImpl implements TranslatorProfileService{
    private final TranslatorProfileRepository translatorProfileRepository;

    @Autowired
    public TranslatorProfileServiceImpl(TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }

    public List<TranslatorProfile> getAllTranslatorProfiles() {
        return translatorProfileRepository.findAll();
    }

    public TranslatorProfile getTranslatorProfileById(Long id) throws Exception {
        return translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileDto) {
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setDateOfBirth(translatorProfileDto.getDateOfBirth());
        translatorProfile.setEmail(translatorProfileDto.getEmail());
        translatorProfile.setIsAvailable(translatorProfileDto.getIsAvailable());
        translatorProfile.setIsOnline(translatorProfileDto.getIsOnline());
        translatorProfile.setLevelOfKorean(translatorProfileDto.getLevelOfKorean());
        translatorProfile.setLanguages(translatorProfileDto.getLanguages());
        translatorProfile.setUser(translatorProfileDto.getUser());
        return translatorProfileRepository.save(translatorProfile);
    }

    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileDto) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
        if (translatorProfileDto.getDateOfBirth() != null) {translatorProfile.setDateOfBirth(translatorProfileDto.getDateOfBirth());}
        if (translatorProfileDto.getEmail() != null) {translatorProfile.setEmail(translatorProfileDto.getEmail());}
        if (translatorProfileDto.getIsAvailable() != null) {translatorProfile.setIsAvailable(translatorProfileDto.getIsAvailable());}
        if (translatorProfileDto.getIsOnline() != null) {translatorProfile.setIsOnline(translatorProfileDto.getIsOnline());}
        if (translatorProfileDto.getLevelOfKorean() != null) {translatorProfile.setLevelOfKorean(translatorProfileDto.getLevelOfKorean());}
        if (translatorProfileDto.getLanguages() != null) {translatorProfile.setLanguages(translatorProfileDto.getLanguages());}
        if (translatorProfileDto.getUser() != null) {translatorProfile.setUser(translatorProfileDto.getUser());}
        return translatorProfileRepository.save(translatorProfile);
    }

    public void deleteTranslatorProfile(Long id) throws Exception {
        translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
        translatorProfileRepository.deleteById(id);
    }
}
