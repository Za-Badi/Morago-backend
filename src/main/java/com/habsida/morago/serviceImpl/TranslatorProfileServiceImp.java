package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.model.inputs.TranslatorPage;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.repository.TranslatorProfileRepository;
import com.habsida.morago.repository.TranslatorProfileRepositoryPaged;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslatorProfileServiceImp {
    private final TranslatorProfileRepository translatorProfileRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final TranslatorProfileRepositoryPaged translatorProfileRepositoryPaged;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public TranslatorPage getAllTranslatorProfilesPaged(PagingInput input) {
        Page<TranslatorProfile> page = translatorProfileRepositoryPaged.findAll(PageUtil.buildPageable(input));
        List<TranslatorProfileDTO> dtoList = page.map(profile -> modelMapperUtil.map(profile, TranslatorProfileDTO.class)).getContent();
        return new TranslatorPage(
                dtoList,
                page.getTotalPages(),
                (int)page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public TranslatorProfileDTO getTranslatorProfileById(Long id) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("TranslatorProfile not found with id: " + id));
        return modelMapperUtil.map(translatorProfile, TranslatorProfileDTO.class);
    }

    @Transactional(readOnly = true)
    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(PagingInput input, Long id, Boolean isOnline) {
        Page<TranslatorProfile> page = translatorProfileRepositoryPaged.findByIsOnlineAndThemesId(isOnline, id, PageUtil.buildPageable(input));
        List<TranslatorProfileDTO> dtoList = page.map(profile -> modelMapperUtil.map(profile, TranslatorProfileDTO.class)).getContent();
        return new TranslatorPage(
                dtoList,
                page.getTotalPages(),
                (int)page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public TranslatorPage getTranslatorProfilesByThemeIdPaged(PagingInput input, Long id) {
        Page<TranslatorProfile> page = translatorProfileRepositoryPaged.findByThemesId(id, PageUtil.buildPageable(input));
        List<TranslatorProfileDTO> dtoList = page.map(profile -> modelMapperUtil.map(profile, TranslatorProfileDTO.class)).getContent();
        return new TranslatorPage(
                dtoList,
                page.getTotalPages(),
                (int)page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public TranslatorPage getTranslatorProfilesByThemeNamePaged(PagingInput input, String name) {
        Page<TranslatorProfile> page = translatorProfileRepositoryPaged.findByThemesName(name, PageUtil.buildPageable(input));
        List<TranslatorProfileDTO> dtoList = page.map(profile -> modelMapperUtil.map(profile, TranslatorProfileDTO.class)).getContent();
        return new TranslatorPage(
                dtoList,
                page.getTotalPages(),
                (int)page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public TranslatorPage searchTranslators(String searchInput, PagingInput input) {
        Page<TranslatorProfile> page = translatorProfileRepositoryPaged.searchTranslatorProfileByEmailOrLevelOfKoreanOrDateOfBirthOrLanguages(searchInput, PageUtil.buildPageable(input));
        List<TranslatorProfileDTO> dtoList = page.map(profile -> modelMapperUtil.map(profile, TranslatorProfileDTO.class)).getContent();
        return new TranslatorPage(
                dtoList,
                page.getTotalPages(),
                (int)page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }

    @Transactional
    public TranslatorProfileDTO addTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = new TranslatorProfile();
        modelMapperUtil.map(translatorProfileInput, translatorProfile);
        translatorProfile.setLanguages(fetchLanguages(translatorProfileInput.getLanguages()));
        translatorProfile.setThemes(new ArrayList<>());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        if (user.getUserProfile() != null) {
            throw new ExceptionGraphql("User has a User Profile attached");
        }
        if (user.getTranslatorProfile() != null) {
            throw new ExceptionGraphql("User already has a Translator Profile attached");
        }
        user.setTranslatorProfile(translatorProfile);
        userRepository.save(user);
        return modelMapperUtil.map(user.getTranslatorProfile(), TranslatorProfileDTO.class);
    }

    @Transactional
    public TranslatorProfileDTO updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("TranslatorProfile not found with id: " + id));
        modelMapperUtil.map(translatorProfileInput, translatorProfile);
        if (translatorProfileInput.getLanguages() != null) {
            translatorProfile.setLanguages(fetchLanguages(translatorProfileInput.getLanguages()));
        }
        return modelMapperUtil.map(translatorProfileRepository.save(translatorProfile), TranslatorProfileDTO.class);
    }

    @Transactional
    public void deleteTranslatorProfile(Long id) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("TranslatorProfile not found with id: " + id));
        if (translatorProfile.getUser() != null) {
            User user = translatorProfile.getUser();
            user.setTranslatorProfile(null);
            translatorProfile.setUser(null);
            userRepository.save(user);
        }
        translatorProfileRepository.delete(translatorProfile);
    }

    @Transactional
    public TranslatorProfileDTO updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        TranslatorProfile translatorProfile = user.getTranslatorProfile();
        if (translatorProfile == null) {
            throw new ExceptionGraphql("User doesn't have a Translator Profile attached");
        }
        modelMapperUtil.map(translatorProfileInput, translatorProfile);
        if (translatorProfileInput.getLanguages() != null) {
            translatorProfile.setLanguages(fetchLanguages(translatorProfileInput.getLanguages()));
        }
        return modelMapperUtil.map(translatorProfileRepository.save(translatorProfile), TranslatorProfileDTO.class);
    }

    @Transactional
    public void changeIsAvailable(Long id, Boolean isAvailable) throws ExceptionGraphql {
        int rowsUpdated = translatorProfileRepository.updateIsAvailableById(id, isAvailable);
        if (rowsUpdated == 0) {
            throw new ExceptionGraphql("Translator Profile not found with id: " + id);
        }
    }

    @Transactional
    public void changeIsOnline(Long id, Boolean isOnline) throws ExceptionGraphql {
        int rowsUpdated = translatorProfileRepository.updateIsOnlineById(id, isOnline);
        if (rowsUpdated == 0) {
            throw new ExceptionGraphql("Translator Profile not found with id: " + id);
        }
    }

    @Transactional
    public TranslatorProfileDTO addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(translatorProfileId)
                .orElseThrow(() -> new ExceptionGraphql("Translator Profile not found with id: " + translatorProfileId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + languageId));
        if (!translatorProfile.getLanguages().contains(language)) {
            translatorProfile.getLanguages().add(language);
        } else {
            throw new ExceptionGraphql("User already has this language");
        }
        translatorProfileRepository.save(translatorProfile);
        return modelMapperUtil.map(translatorProfile, TranslatorProfileDTO.class);
    }

    @Transactional
    public void deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(translatorProfileId)
                .orElseThrow(() -> new ExceptionGraphql("Translator Profile not found with id: " + translatorProfileId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + languageId));
        translatorProfile.getLanguages().remove(language);
        translatorProfileRepository.save(translatorProfile);
    }

    @Transactional
    public UserDTO changeBalanceForTranslatorProfileId(Long id, Double balance) throws ExceptionGraphql {
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Translator Profile not found with id: " + id));
        User user = translatorProfile.getUser();
        user.setBalance(balance);
        return modelMapperUtil.map(userRepository.save(user), UserDTO.class);
    }

    private List<Language> fetchLanguages(List<Long> languageIds) throws ExceptionGraphql {
        List<Language> languages = new ArrayList<>();
        if (languageIds != null) {
            for (Long languageId : languageIds) {
                Language language = languageRepository.findById(languageId)
                        .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + languageId));
                languages.add(language);
            }
        }
        return languages;
    }
}
