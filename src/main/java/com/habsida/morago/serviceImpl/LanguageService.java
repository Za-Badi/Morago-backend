package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.LanguagePageOutput;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public LanguagePageOutput getAllLanguages(PagingInput pagingInput) {
        Page<Language> languagePage = languageRepository.findAll(PageUtil.buildPageable(pagingInput));
        return new LanguagePageOutput(
                languagePage.getNumber(),
                languagePage.getTotalPages(),
                languagePage.getTotalElements(),
                languagePage.getContent().stream()
                        .map(language -> modelMapperUtil.map(language, LanguageDTO.class))
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public LanguageDTO getLanguageById(Long id) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        return modelMapperUtil.map(language, LanguageDTO.class);
    }

    @Transactional
    public LanguageDTO addLanguage(LanguageInput languageInput) {
        if (languageInput.getName() == null || languageInput.getName().isBlank()) {
            throw new IllegalArgumentException("Language name cannot be null or blank");
        }
        Language language = new Language();
        language.setName(languageInput.getName());
        Language savedLanguage = languageRepository.save(language);
        return modelMapperUtil.map(savedLanguage, LanguageDTO.class);
    }

    @Transactional
    public LanguageDTO updateLanguage(Long id, LanguageInput languageInput) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        if (languageInput.getName() != null && !languageInput.getName().isBlank()) {
            language.setName(languageInput.getName());
        }
        Language updatedLanguage = languageRepository.save(language);
        return modelMapperUtil.map(updatedLanguage, LanguageDTO.class);
    }

    @Transactional
    public void deleteLanguage(Long id) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        languageRepository.delete(language);
    }
}
