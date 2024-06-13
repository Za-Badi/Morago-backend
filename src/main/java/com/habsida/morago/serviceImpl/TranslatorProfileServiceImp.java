package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.repository.ThemeRepository;
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
    private final ThemeRepository themeRepository;

    @Autowired
    public TranslatorProfileServiceImp(TranslatorProfileRepository translatorProfileRepository, LanguageRepository languageRepository, UserRepository userRepository, ThemeRepository themeRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
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
    public List<TranslatorProfile> getTranslatorProfilesByThemeId(Long id) throws Exception {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new Exception("Theme not found with id: " + id));
        List<TranslatorProfile> translatorProfilesByThemeId = new ArrayList<>();
        for (TranslatorProfile translatorProfile : translatorProfileRepository.findAll()) {
            if (translatorProfile.getThemes().contains(theme)) {
                translatorProfilesByThemeId.add(translatorProfile);
            }
        }
        return translatorProfilesByThemeId;
    }
    public List<TranslatorProfile> getTranslatorProfilesByThemeName(String name) throws Exception {
        Theme theme = themeRepository.findByName(name)
                .orElseThrow(() -> new Exception("Theme not found with name: " + name));
        List<TranslatorProfile> translatorProfilesByThemeName = new ArrayList<>();
        for (TranslatorProfile translatorProfile : translatorProfileRepository.findAll()) {
            if (translatorProfile.getThemes().contains(theme)) {
                translatorProfilesByThemeName.add(translatorProfile);
            }
        }
        return translatorProfilesByThemeName;
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfileInput translatorProfileInput) throws Exception {
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        translatorProfile.setEmail(translatorProfileInput.getEmail());
        translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
        translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        List<Language> languages = new ArrayList<>();
        if (translatorProfileInput.getLanguages() != null) {
            for (Long languageId : translatorProfileInput.getLanguages()) {
                Language language = languageRepository.findById(languageId)
                        .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
                languages.add(language);
            }
        }
        translatorProfile.setLanguages(languages);
        List<Theme> themes = new ArrayList<>();
        translatorProfile.setThemes(themes);
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

    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        Long newId = user.getTranslatorProfile().getId();
        if (newId==null) {
            throw new Exception("User doesn't have Translator Profile");
        }
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(newId)
                .orElseThrow(() -> new Exception("TranslatorProfile not found with id: " + newId));
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
    public boolean changeIsAvailable(Long id) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("Translator Profile not found with id: " + id));
        translatorProfile.setIsAvailable(!translatorProfile.getIsAvailable());
        translatorProfileRepository.save(translatorProfile);
        return true;
    }
    public boolean changeIsOnline(Long id) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("Translator Profile not found with id: " + id));
        translatorProfile.setIsOnline(!translatorProfile.getIsOnline());
        translatorProfileRepository.save(translatorProfile);
        return true;
    }
    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(translatorProfileId)
                .orElseThrow(() -> new Exception("Translator Profile not found with id: " + translatorProfileId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
        if(!translatorProfile.getLanguages().contains(language)) {
            translatorProfile.getLanguages().add(language);
        } else {
            throw new Exception("User already has this language");
        }
        translatorProfileRepository.save(translatorProfile);
        return translatorProfile;
    }
    public void deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(translatorProfileId)
                .orElseThrow(() -> new Exception("Translator Profile not found with id: " + translatorProfileId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
        translatorProfile.getLanguages().remove(language);
        translatorProfileRepository.save(translatorProfile);
    }
    public User changeBalanceForTranslatorProfileId(Long id, Float balance) throws Exception {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new Exception("Translator Profile not found with id: " + id));
        User user = userRepository.findById(translatorProfile.getUser().getId())
                .orElseThrow(() -> new Exception("User not found with id: " + id));
        Double newBalance = balance.doubleValue();
        user.setBalance(newBalance);
        return userRepository.save(user);
    }
}