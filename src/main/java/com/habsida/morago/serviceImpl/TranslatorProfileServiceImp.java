//package com.habsida.morago.serviceImpl;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.entity.*;
//import com.habsida.morago.model.inputs.TranslatorPage;
//import com.habsida.morago.model.inputs.TranslatorProfileInput;
//import com.habsida.morago.model.inputs.UserPage;
//import com.habsida.morago.repository.*;
//import com.habsida.morago.service.TranslatorProfileService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class TranslatorProfileServiceImp implements TranslatorProfileService {
//    private final TranslatorProfileRepository translatorProfileRepository;
//    private final LanguageRepository languageRepository;
//    private final UserRepository userRepository;
//    private final TranslatorProfileRepositoryPaged translatorProfileRepositoryPaged;
//
//    public List<TranslatorProfile> getAllTranslatorProfiles() {
//        return translatorProfileRepository.findAll();
//    }
//
//    public TranslatorPage getAllTranslatorProfilesPaged(Integer page, Integer size) {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
//        Page<TranslatorProfile> translatorProfilePage = translatorProfileRepositoryPaged.findAll(PageRequest.of(page, size));
//        return new TranslatorPage(
//                translatorProfilePage.getContent(),
//                translatorProfilePage.getTotalPages(),
//                (int) translatorProfilePage.getTotalElements(),
//                translatorProfilePage.getSize(),
//                translatorProfilePage.getNumber()
//        );
//    }
//
//    public TranslatorProfile getTranslatorProfileById(Long id) throws GlobalException {
//        return translatorProfileRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("TranslatorProfile not found with id: " + id));
//    }
//    public List<TranslatorProfile> getTranslatorProfilesByIsOnlineAndThemeId(Boolean isOnline, Long id) {
//        return translatorProfileRepository.findByIsOnlineAndThemesId(isOnline, id);
//    }
//    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(Integer page, Integer size, Long id, Boolean isOnline) {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
//        Page<TranslatorProfile> translatorProfilePage = translatorProfileRepositoryPaged.findByIsOnlineAndThemesId(isOnline, id ,PageRequest.of(page, size));
//        return new TranslatorPage(
//                translatorProfilePage.getContent(),
//                translatorProfilePage.getTotalPages(),
//                (int) translatorProfilePage.getTotalElements(),
//                translatorProfilePage.getSize(),
//                translatorProfilePage.getNumber()
//        );
//    }
//    public List<TranslatorProfile> getTranslatorProfilesByThemeId(Long id) {
//        return translatorProfileRepository.findByThemesId(id);
//    }
//    public TranslatorPage getTranslatorProfilesByThemeIdPaged(Integer page, Integer size, Long id) {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
//        Page<TranslatorProfile> translatorProfilePage = translatorProfileRepositoryPaged.findByThemesId(id, PageRequest.of(page, size));
//        return new TranslatorPage(
//                translatorProfilePage.getContent(),
//                translatorProfilePage.getTotalPages(),
//                (int) translatorProfilePage.getTotalElements(),
//                translatorProfilePage.getSize(),
//                translatorProfilePage.getNumber()
//        );
//    }
//    public List<TranslatorProfile> getTranslatorProfilesByThemeName(String name)  {
//        return translatorProfileRepository.findByThemesName(name);
//    }
//    public TranslatorPage getTranslatorProfilesByThemeNamePaged(Integer page, Integer size, String name) {
//        if (page == null) {
//            page = 0;
//        }
//        if (size == null) {
//            size = 10;
//        }
//        Page<TranslatorProfile> translatorProfilePage = translatorProfileRepositoryPaged.findByThemesName(name, PageRequest.of(page, size));
//        return new TranslatorPage(
//                translatorProfilePage.getContent(),
//                translatorProfilePage.getTotalPages(),
//                (int) translatorProfilePage.getTotalElements(),
//                translatorProfilePage.getSize(),
//                translatorProfilePage.getNumber()
//        );
//    }
//
//    public TranslatorProfile addTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException {
//        TranslatorProfile translatorProfile = new TranslatorProfile();
//        translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
//        translatorProfile.setEmail(translatorProfileInput.getEmail());
//        translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
//        translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
//        translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
//        List<Language> languages = new ArrayList<>();
//        if (translatorProfileInput.getLanguages() != null) {
//            for (Long languageId : translatorProfileInput.getLanguages()) {
//                Language language = languageRepository.findById(languageId)
//                        .orElseThrow(() -> new GlobalException("Language not found with id: " + languageId));
//                languages.add(language);
//            }
//        }
//        translatorProfile.setLanguages(languages);
//        List<Theme> themes = new ArrayList<>();
//        translatorProfile.setThemes(themes);
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        if (user.getTranslatorProfile() == null) {
//            user.setTranslatorProfile(translatorProfile);
//            userRepository.save(user);
//        } else {
//            throw new GlobalException("User already has a Profile attached");
//        }
//        return user.getTranslatorProfile();
//    }
//
//    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("TranslatorProfile not found with id: " + id));
//        if (translatorProfileInput.getDateOfBirth() != null && !translatorProfileInput.getDateOfBirth().isBlank()) {
//            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
//        }
//        if (translatorProfileInput.getEmail() != null && !translatorProfileInput.getEmail().isBlank()) {
//            translatorProfile.setEmail(translatorProfileInput.getEmail());
//        }
//        if (translatorProfileInput.getIsAvailable() != null) {
//            translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
//        }
//        if (translatorProfileInput.getIsOnline() != null) {
//            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
//        }
//        if (translatorProfileInput.getLevelOfKorean() != null && !translatorProfileInput.getLevelOfKorean().isBlank()) {
//            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
//        }
//        if (translatorProfileInput.getLanguages() != null) {
//            translatorProfile.getLanguages().clear();
//            List<Language> languages = new ArrayList<>();
//            for (Long languageId : translatorProfileInput.getLanguages()) {
//                Language language = languageRepository.findById(languageId)
//                        .orElseThrow(() -> new GlobalException("Language not found with id: " + languageId));
//                languages.add(language);
//            }
//            translatorProfile.setLanguages(languages);
//        }
//        return translatorProfileRepository.save(translatorProfile);
//    }
//
//    public void deleteTranslatorProfile(Long id) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("TranslatorProfile not found with id: " + id));
//        if (translatorProfile.getUser() != null){
//            User user = translatorProfile.getUser();
//            user.setTranslatorProfile(null);
//            translatorProfile.setUser(null);
//            userRepository.save(user);
//        }
//        translatorProfile.getLanguages().clear();
//        translatorProfile.getThemes().clear();
//        translatorProfileRepository.save(translatorProfile);
//        translatorProfileRepository.deleteById(id);
//    }
//
//    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws GlobalException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        Long newId = user.getTranslatorProfile().getId();
//        if (newId==null) {
//            throw new GlobalException("User doesn't have Translator Profile");
//        }
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(newId)
//                .orElseThrow(() -> new GlobalException("TranslatorProfile not found with id: " + newId));
//        if (translatorProfileInput.getDateOfBirth() != null && !translatorProfileInput.getDateOfBirth().isBlank()) {
//            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
//        }
//        if (translatorProfileInput.getEmail() != null && !translatorProfileInput.getEmail().isBlank()) {
//            translatorProfile.setEmail(translatorProfileInput.getEmail());
//        }
//        if (translatorProfileInput.getIsAvailable() != null) {
//            translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
//        }
//        if (translatorProfileInput.getIsOnline() != null) {
//            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
//        }
//        if (translatorProfileInput.getLevelOfKorean() != null && !translatorProfileInput.getLevelOfKorean().isBlank()) {
//            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
//        }
//        if (translatorProfileInput.getLanguages() != null) {
//            translatorProfile.getLanguages().clear();
//            List<Language> languages = new ArrayList<>();
//            for (Long languageId : translatorProfileInput.getLanguages()) {
//                Language language = languageRepository.findById(languageId)
//                        .orElseThrow(() -> new GlobalException("Language not found with id: " + languageId));
//                languages.add(language);
//            }
//            translatorProfile.setLanguages(languages);
//        }
//        return translatorProfileRepository.save(translatorProfile);
//    }
//    public TranslatorProfile changeIsAvailable(Long id, Boolean isAvailable) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("Translator Profile not found with id: " + id));
//        translatorProfile.setIsAvailable(isAvailable);
//        return translatorProfileRepository.save(translatorProfile);
//    }
//    public TranslatorProfile changeIsOnline(Long id, Boolean isOnline) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("Translator Profile not found with id: " + id));
//        translatorProfile.setIsOnline(isOnline);
//        return translatorProfileRepository.save(translatorProfile);
//    }
//    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(translatorProfileId)
//                .orElseThrow(() -> new GlobalException("Translator Profile not found with id: " + translatorProfileId));
//        Language language = languageRepository.findById(languageId)
//                .orElseThrow(() -> new GlobalException("Language not found with id: " + languageId));
//        if(!translatorProfile.getLanguages().contains(language)) {
//            translatorProfile.getLanguages().add(language);
//        } else {
//            throw new GlobalException("User already has this language");
//        }
//        translatorProfileRepository.save(translatorProfile);
//        return translatorProfile;
//    }
//    public void deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(translatorProfileId)
//                .orElseThrow(() -> new GlobalException("Translator Profile not found with id: " + translatorProfileId));
//        Language language = languageRepository.findById(languageId)
//                .orElseThrow(() -> new GlobalException("Language not found with id: " + languageId));
//        translatorProfile.getLanguages().remove(language);
//        translatorProfileRepository.save(translatorProfile);
//    }
//    public User changeBalanceForTranslatorProfileId(Long id, Float balance) throws GlobalException {
//        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
//                .orElseThrow(() -> new GlobalException("Translator Profile not found with id: " + id));
//        User user = userRepository.findById(translatorProfile.getUser().getId())
//                .orElseThrow(() -> new GlobalException("User not found with id: " + id));
//        Double newBalance = balance.doubleValue();
//        user.setBalance(newBalance);
//        return userRepository.save(user);
//    }
//}