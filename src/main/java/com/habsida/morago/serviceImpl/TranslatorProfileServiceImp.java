package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.repository.TranslatorProfileRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.TranslatorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TranslatorProfileServiceImp implements TranslatorProfileService {
    private final TranslatorProfileRepository translatorProfileRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    @Autowired
    public TranslatorProfileServiceImp(TranslatorProfileRepository translatorProfileRepository, LanguageRepository languageRepository, UserRepository userRepository) {
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
    public List<TranslatorProfile> getTranslatorProfilesByIsOnline(Boolean isOnline) {
        List<TranslatorProfile> translatorProfileList = translatorProfileRepository.findAll();
        List<TranslatorProfile> isOnlineList = new ArrayList<>();
        for (TranslatorProfile translatorProfile: translatorProfileList) {
            if (translatorProfile.getIsOnline() == isOnline) {
                isOnlineList.add(translatorProfile);
            }
        }
        return isOnlineList;
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileInput) throws Exception {
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        translatorProfile.setEmail(translatorProfileInput.getEmail());
        translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
        translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        List<Language> languages = new ArrayList<>();
        for (Long languageId : translatorProfileInput.getLanguages()) {
            Language language = languageRepository.findById(languageId)
                    .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
            languages.add(language);
        }
        translatorProfile.setLanguages(languages);
        return translatorProfileRepository.save(translatorProfile);
    }

    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
        if (translatorProfileInput.getDateOfBirth() != null && !translatorProfileInput.getDateOfBirth().trim().isEmpty()) {
            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        }
        if (translatorProfileInput.getEmail() != null && !translatorProfileInput.getEmail().trim().isEmpty()) {
            translatorProfile.setEmail(translatorProfileInput.getEmail());
        }
        if (translatorProfileInput.getIsAvailable() != null) {
            translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
        }
        if (translatorProfileInput.getIsOnline() != null) {
            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        }
        if (translatorProfileInput.getLevelOfKorean() != null && !translatorProfileInput.getLevelOfKorean().trim().isEmpty()) {
            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        }
        if (translatorProfileInput.getLanguages() != null) {
            translatorProfile.getLanguages().clear();
            List<Language> languages = new ArrayList<>();
            for (Long languageId : translatorProfileInput.getLanguages()) {
                Language language = languageRepository.findById(languageId)
                        .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
                languages.add(language);
            }
            translatorProfile.setLanguages(languages);
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    public void deleteTranslatorProfile(Long id) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + id));
        if (translatorProfile.getUser() != null){
            User user = translatorProfile.getUser();
            user.setTranslatorProfile(null);
            translatorProfile.setUser(null);
            userRepository.save(user);
        }
        translatorProfile.getLanguages().clear();
        translatorProfileRepository.save(translatorProfile);
        translatorProfileRepository.deleteById(id);
    }
}