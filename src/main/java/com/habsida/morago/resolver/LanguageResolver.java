package com.habsida.morago.resolver;

import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageResolver {
    private final LanguageService languageService;

    @Autowired
    public LanguageResolver(LanguageService languageService) {
        this.languageService = languageService;
    }

    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    public Language getLanguageById(Long id) throws Exception {
        return languageService.getLanguageById(id);
    }

    public Language addLanguage(LanguageInput languageInput) {
        return languageService.addLanguage(languageInput);
    }

    public Language updateLanguage(Long id, LanguageInput languageInput) throws Exception {
        return languageService.updateLanguage(id, languageInput);
    }

    public Boolean deleteLanguage(Long id) throws Exception {
        languageService.deleteLanguage(id);
        return true;
    }
}