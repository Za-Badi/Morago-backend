package com.habsida.morago.services;

import com.habsida.morago.dtos.TranslatorProfileInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.repository.TranslatorProfileRepository;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranslatorProfileServiceImpl implements TranslatorProfileService{
    private final TranslatorProfileRepository translatorProfileRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    @Autowired
    public TranslatorProfileServiceImpl(TranslatorProfileRepository translatorProfileRepository, LanguageRepository languageRepository, UserRepository userRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    public List<TranslatorProfile> getAllTranslatorProfiles() {
        return translatorProfileRepository.findAll();
    }

    public TranslatorProfile getTranslatorProfileById(Long id) throws Exception {
        return translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileDto) throws Exception {
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setDateOfBirth(translatorProfileDto.getDateOfBirth());
        translatorProfile.setEmail(translatorProfileDto.getEmail());
        translatorProfile.setIsAvailable(translatorProfileDto.getIsAvailable());
        translatorProfile.setIsOnline(translatorProfileDto.getIsOnline());
        translatorProfile.setLevelOfKorean(translatorProfileDto.getLevelOfKorean());
        List<Language> languages = new ArrayList<>();
        for (Long languageId : translatorProfileDto.getLanguages()) {
            Language language = languageRepository.findById(languageId)
                    .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
            languages.add(language);
        }
        translatorProfile.setLanguages(languages);
        Long userId = translatorProfileDto.getUser();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id: " + userId));
        user.setTranslatorProfile(translatorProfile);
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
        if (translatorProfileDto.getLanguages() != null) {
            List<Language> languages = new ArrayList<>();
            for (Long languageId : translatorProfileDto.getLanguages()) {
                Language language = languageRepository.findById(languageId)
                        .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
                languages.add(language);
            }
            translatorProfile.setLanguages(languages);
        }
        if (translatorProfileDto.getUser() != null) {
            Long userId = translatorProfileDto.getUser();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new Exception("User not found with id: " + userId));
            user.setTranslatorProfile(translatorProfile);
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    public void deleteTranslatorProfile(Long id) throws Exception {
        translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
        translatorProfileRepository.deleteById(id);
    }
}
