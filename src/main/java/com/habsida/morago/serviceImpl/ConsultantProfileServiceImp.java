package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.ConsultantProfileDTO;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.ConsultantProfile;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.ConsultantPage;
import com.habsida.morago.model.inputs.ConsultantProfileInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.repository.ConsultantProfileRepository;
import com.habsida.morago.repository.ConsultantProfileRepositoryPaged;
import com.habsida.morago.repository.LanguageRepository;
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
public class ConsultantProfileServiceImp {
    private final ConsultantProfileRepository consultantProfileRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final ConsultantProfileRepositoryPaged consultantProfileRepositoryPaged;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public ConsultantPage getAllConsultantProfilesPaged(PagingInput pagingInput) {
        Page<ConsultantProfile> consultantProfilePage = consultantProfileRepositoryPaged.findAll(PageUtil.buildPageable(pagingInput));
        List<ConsultantProfileDTO> dtoList = consultantProfilePage.getContent().stream()
                .map(consultantProfile -> modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class))
                .collect(Collectors.toList());
        return new ConsultantPage(
                dtoList,
                consultantProfilePage.getTotalPages(),
                (int) consultantProfilePage.getTotalElements(),
                consultantProfilePage.getSize(),
                consultantProfilePage.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public ConsultantProfileDTO getConsultantProfileById(Long id) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = consultantProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("ConsultantProfile not found with id: " + id));
        return modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class);
    }

    @Transactional(readOnly = true)
    public ConsultantPage getConsultantProfilesByIsOnlineAndLanguageIdPaged(PagingInput pagingInput, Long id, Boolean isOnline) {
        Page<ConsultantProfile> consultantProfilePage = consultantProfileRepositoryPaged.findByIsOnlineAndLanguagesId(isOnline, id, PageUtil.buildPageable(pagingInput));
        List<ConsultantProfileDTO> dtoList = consultantProfilePage.getContent().stream()
                .map(consultantProfile -> modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class))
                .collect(Collectors.toList());
        return new ConsultantPage(
                dtoList,
                consultantProfilePage.getTotalPages(),
                (int) consultantProfilePage.getTotalElements(),
                consultantProfilePage.getSize(),
                consultantProfilePage.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public ConsultantPage getConsultantProfilesByLanguageIdPaged(PagingInput pagingInput, Long id) {
        Page<ConsultantProfile> consultantProfilePage = consultantProfileRepositoryPaged.findByLanguagesId(id, PageUtil.buildPageable(pagingInput));
        List<ConsultantProfileDTO> dtoList = consultantProfilePage.getContent().stream()
                .map(consultantProfile -> modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class))
                .collect(Collectors.toList());
        return new ConsultantPage(
                dtoList,
                consultantProfilePage.getTotalPages(),
                (int) consultantProfilePage.getTotalElements(),
                consultantProfilePage.getSize(),
                consultantProfilePage.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public ConsultantPage getConsultantProfilesByLanguageNamePaged(PagingInput pagingInput, String name) {
        Page<ConsultantProfile> consultantProfilePage = consultantProfileRepositoryPaged.findByLanguagesName(name, PageUtil.buildPageable(pagingInput));
        List<ConsultantProfileDTO> dtoList = consultantProfilePage.getContent().stream()
                .map(consultantProfile -> modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class))
                .collect(Collectors.toList());
        return new ConsultantPage(
                dtoList,
                consultantProfilePage.getTotalPages(),
                (int) consultantProfilePage.getTotalElements(),
                consultantProfilePage.getSize(),
                consultantProfilePage.getNumber()
        );
    }

    @Transactional(readOnly = true)
    public ConsultantPage searchConsultants(String searchInput, PagingInput pagingInput) {
        Page<ConsultantProfile> consultantProfilePage = consultantProfileRepositoryPaged.searchConsultantProfileByEmailOrDateOfBirthOrLanguages(searchInput, PageUtil.buildPageable(pagingInput));
        List<ConsultantProfileDTO> dtoList = consultantProfilePage.getContent().stream()
                .map(consultantProfile -> modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class))
                .collect(Collectors.toList());
        return new ConsultantPage(
                dtoList,
                consultantProfilePage.getTotalPages(),
                (int) consultantProfilePage.getTotalElements(),
                consultantProfilePage.getSize(),
                consultantProfilePage.getNumber()
        );
    }

    @Transactional
    public ConsultantProfileDTO addConsultantProfile(Long id, ConsultantProfileInput consultantProfileInput) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = new ConsultantProfile();
        modelMapperUtil.map(consultantProfileInput, consultantProfile);
        consultantProfile.setLanguages(fetchLanguages(consultantProfileInput.getLanguages()));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        if (user.getUserProfile() != null) {
            throw new ExceptionGraphql("User has a User Profile attached");
        }
        if (user.getConsultantProfile() != null) {
            throw new ExceptionGraphql("User already has a Consultant Profile attached");
        }
        user.setConsultantProfile(consultantProfile);
        userRepository.save(user);
        return modelMapperUtil.map(user.getConsultantProfile(), ConsultantProfileDTO.class);
    }

    @Transactional
    public ConsultantProfileDTO updateConsultantProfile(Long id, ConsultantProfileInput consultantProfileInput) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = consultantProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("ConsultantProfile not found with id: " + id));
        modelMapperUtil.map(consultantProfileInput, consultantProfile);
        if (consultantProfileInput.getLanguages() != null) {
            consultantProfile.setLanguages(fetchLanguages(consultantProfileInput.getLanguages()));
        }
        return modelMapperUtil.map(consultantProfileRepository.save(consultantProfile), ConsultantProfileDTO.class);
    }

    @Transactional
    public void deleteConsultantProfile(Long id) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = consultantProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("ConsultantProfile not found with id: " + id));
        if (consultantProfile.getUser() != null) {
            User user = consultantProfile.getUser();
            user.setConsultantProfile(null);
            consultantProfile.setUser(null);
            userRepository.save(user);
        }
        consultantProfile.getLanguages().clear();
        consultantProfileRepository.save(consultantProfile);
        consultantProfileRepository.deleteById(id);
    }

    @Transactional
    public ConsultantProfileDTO updateConsultantProfileByUserId(Long id, ConsultantProfileInput consultantProfileInput) throws ExceptionGraphql {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
        ConsultantProfile consultantProfile = user.getConsultantProfile();
        if (consultantProfile == null) {
            throw new ExceptionGraphql("User doesn't have a Consultant Profile attached");
        }
        modelMapperUtil.map(consultantProfileInput, consultantProfile);
        if (consultantProfileInput.getLanguages() != null) {
            consultantProfile.setLanguages(fetchLanguages(consultantProfileInput.getLanguages()));
        }
        return modelMapperUtil.map(consultantProfileRepository.save(consultantProfile), ConsultantProfileDTO.class);
    }

    @Transactional
    public Boolean changeIsAvailableForConsultant(Long id, Boolean isAvailable) throws ExceptionGraphql {
        int rowsUpdated = consultantProfileRepository.updateIsAvailableById(id, isAvailable);
        if (rowsUpdated == 0) {
            throw new ExceptionGraphql("Consultant Profile not found with id: " + id);
        }
        return true;
    }

    @Transactional
    public Boolean changeIsOnlineForConsultant(Long id, Boolean isOnline) throws ExceptionGraphql {
        int rowsUpdated = consultantProfileRepository.updateIsOnlineById(id, isOnline);
        if (rowsUpdated == 0) {
            throw new ExceptionGraphql("Consultant Profile not found with id: " + id);
        }
        return true;
    }

    @Transactional
    public ConsultantProfileDTO addLanguageToConsultantProfile(Long languageId, Long consultantProfileId) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = consultantProfileRepository.findById(consultantProfileId)
                .orElseThrow(() -> new ExceptionGraphql("Consultant Profile not found with id: " + consultantProfileId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + languageId));
        if (!consultantProfile.getLanguages().contains(language)) {
            consultantProfile.getLanguages().add(language);
        } else {
            throw new ExceptionGraphql("User already has this language");
        }
        consultantProfileRepository.save(consultantProfile);
        return modelMapperUtil.map(consultantProfile, ConsultantProfileDTO.class);
    }

    @Transactional
    public void deleteLanguageFromConsultantProfile(Long languageId, Long consultantProfileId) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = consultantProfileRepository.findById(consultantProfileId)
                .orElseThrow(() -> new ExceptionGraphql("Consultant Profile not found with id: " + consultantProfileId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + languageId));
        consultantProfile.getLanguages().remove(language);
        consultantProfileRepository.save(consultantProfile);
    }

    @Transactional
    public UserDTO changeBalanceForConsultantProfileId(Long id, Double balance) throws ExceptionGraphql {
        ConsultantProfile consultantProfile = consultantProfileRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Consultant Profile not found with id: " + id));
        User user = userRepository.findById(consultantProfile.getUser().getId())
                .orElseThrow(() -> new ExceptionGraphql("User not found with id: " + id));
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
