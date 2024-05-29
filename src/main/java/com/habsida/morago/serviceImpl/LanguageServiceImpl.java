package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Language;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getLanguageById(Long id) throws Exception {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            return languageRepository.findById(id);
        } else {
            throw new Exception("Language not found for id: " + id);
        }
    }

    public Language addLanguage(Language language) {
        languageRepository.save(language);
        return language;
    }

    public Language updateLanguage(Long id, Language languageUpdate) throws Exception {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            Language language = optionalLanguage.get();
            language.setName(languageUpdate.getName());
            language.setTranslatorProfiles(languageUpdate.getTranslatorProfiles());
            return languageRepository.save(language);
        } else {
            throw new Exception("Language not found for id: " + id);
        }
    }

    public void deleteLanguage(Long id) throws Exception {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            languageRepository.deleteById(id);
        } else {
            throw new Exception("Language not found for id: " + id);
        }
    }
}