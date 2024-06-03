package com.habsida.morago.serviceImpl;

import com.habsida.morago.dtos.LanguageInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.User;
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

    public Language getLanguageById(Long id) throws Exception {
        return languageRepository.findById(id)
                .orElseThrow(() -> new Exception("Language not found with id: " + id));
    }

    public Language addLanguage(LanguageInput languageDto) {
        Language language = new Language();
        language.setName(languageDto.getName());
        return languageRepository.save(language);
    }

    public Language updateLanguage(Long id, LanguageInput languageDto) throws Exception {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new Exception("Language not found with id: " + id));
        if (languageDto.getName() != null) { language.setName(languageDto.getName()); }
        return languageRepository.save(language);
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