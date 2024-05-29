package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.repository.TranslatorProfileRepository;
import com.habsida.morago.service.TranslatorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TranslatorProfileServiceImpl implements TranslatorProfileService {
    private final TranslatorProfileRepository translatorProfileRepository;

    @Autowired
    public TranslatorProfileServiceImpl(TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }

    public List<TranslatorProfile> getAllTranslatorProfiles() {
        return translatorProfileRepository.findAll();
    }

    public Optional<TranslatorProfile> getTranslatorProfileById(Long id) throws Exception {
        Optional<TranslatorProfile> optionalTranslatorProfile = translatorProfileRepository.findById(id);
        if (optionalTranslatorProfile.isPresent()) {
            return translatorProfileRepository.findById(id);
        } else {
            throw new Exception("Translator Profile not found for id: " + id);
        }
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfile translatorProfile) {
        return translatorProfileRepository.save(translatorProfile);
    }

    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfile translatorProfileUpdate) throws Exception {
        Optional<TranslatorProfile> optionalTranslatorProfile = translatorProfileRepository.findById(id);
        if (optionalTranslatorProfile.isPresent()) {
            TranslatorProfile translatorProfile = optionalTranslatorProfile.get();
            translatorProfile.setDateOfBirth(translatorProfileUpdate.getDateOfBirth());
            translatorProfile.setEmail(translatorProfileUpdate.getEmail());
            translatorProfile.setIsAvailable(translatorProfileUpdate.getIsAvailable());
            translatorProfile.setIsOnline(translatorProfileUpdate.getIsOnline());
            translatorProfile.setLevelOfKorean(translatorProfileUpdate.getLevelOfKorean());
            translatorProfile.setLanguages(translatorProfileUpdate.getLanguages());
            translatorProfile.setUser(translatorProfileUpdate.getUser());
            return translatorProfileRepository.save(translatorProfile);
        } else {
            throw new Exception("Translator Profile not found for id: " + id);
        }
    }

    public void deleteTranslatorProfile(Long id) throws Exception {
        Optional<TranslatorProfile> optionalTranslatorProfile = translatorProfileRepository.findById(id);
        if (optionalTranslatorProfile.isPresent()) {
            translatorProfileRepository.deleteById(id);
        } else {
            throw new Exception("Translator Profile not found for id: " + id);
        }
    }
}
