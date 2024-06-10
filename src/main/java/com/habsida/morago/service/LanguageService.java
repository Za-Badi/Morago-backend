package com.habsida.morago.service;

import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.entity.Language;

import java.util.List;

public interface LanguageService {
    public List<Language> getAllLanguages();

    public Language getLanguageById(Long id) throws Exception;

    public Language addLanguage(LanguageInput languageDto);

    public Language updateLanguage(Long id, LanguageInput languageDto) throws Exception;

    public void deleteLanguage(Long id) throws Exception;
}