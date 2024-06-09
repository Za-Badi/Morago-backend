package com.habsida.morago.controllers;

import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.resolver.LanguageResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LanguageController {
    private final LanguageResolver languageResolver;
    public LanguageController(LanguageResolver languageResolver) {
        this.languageResolver = languageResolver;
    }
    @QueryMapping
    public List<Language> getAllLanguages() {return languageResolver.getAllLanguages();}
    @QueryMapping
    public Language getLanguageById(@Argument Long id) throws Exception {return languageResolver.getLanguageById(id);}
    @MutationMapping
    public Language addLanguage(@Argument LanguageInput languageInput)  {return  languageResolver.addLanguage(languageInput);}
    @MutationMapping
    public Language updateLanguage(@Argument Long id, @Argument LanguageInput languageInput) throws Exception {return languageResolver.updateLanguage(id, languageInput);}
    @MutationMapping
    public Boolean deleteLanguage(@Argument Long id) throws Exception {return languageResolver.deleteLanguage(id);}
}
