package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService  {
    private final LanguageRepository languageRepository;
    @Transactional(readOnly = true)
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Language getLanguageById(Long id) throws ExceptionGraphql {
        return languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
    }
    @Transactional
    public Language addLanguage(LanguageInput languageInput) {
        Language language = new Language();
        language.setName(languageInput.getName());
        return languageRepository.save(language);
    }
    @Transactional
    public Language updateLanguage(Long id, LanguageInput languageInput) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        if (languageInput.getName() != null && !languageInput.getName().isBlank()) {
            language.setName(languageInput.getName());
        }
        return languageRepository.save(language);
    }
    @Transactional
    public void deleteLanguage(Long id) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        languageRepository.delete(language);
    }
}