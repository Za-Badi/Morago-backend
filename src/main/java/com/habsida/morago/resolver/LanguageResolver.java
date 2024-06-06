package com.habsida.morago.resolvers;

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
    public Language addLanguage(LanguageInput languageDto) {
        return languageService.addLanguage(languageDto);
    }
    public Language updateLanguage(Long id, LanguageInput languageDto) throws Exception {
        return languageService.updateLanguage(id, languageDto);
    }
    public Boolean deleteLanguage(Long id) throws Exception {
        languageService.deleteLanguage(id);
        return true;
    }
}