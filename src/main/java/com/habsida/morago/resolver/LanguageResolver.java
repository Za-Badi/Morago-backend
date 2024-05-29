package com.habsida.morago.resolver;

import com.habsida.morago.model.entity.Language;
import com.habsida.morago.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

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
    public Optional<Language> getLanguageById(Long id) throws Exception {
        return languageService.getLanguageById(id);
    }
    public Language addLanguage(Language language) {
        return languageService.addLanguage(language);
    }
    public Language updateLanguage(Long id, Language languageUpdate) throws Exception {
        return languageService.updateLanguage(id, languageUpdate);
    }
    public Boolean deleteLanguage(Long id) throws Exception {
        languageService.deleteLanguage(id);
        return true;
    }
}
