package com.habsida.morago.services;

import com.habsida.morago.model.entity.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    public List<Language> getAllLanguages();
    public Optional<Language> getLanguageById(Long id) throws Exception;
    public Language addLanguage(Language language);
    public Language updateLanguage(Long id, Language languageUpdate) throws Exception;
    public void deleteLanguage(Long id) throws Exception;
}
