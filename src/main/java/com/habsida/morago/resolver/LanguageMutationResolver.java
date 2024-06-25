package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.serviceImpl.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageMutationResolver implements GraphQLMutationResolver {
    private final LanguageService languageService;

    public LanguageDTO addLanguage(LanguageInput languageInput) {
        return languageService.addLanguage(languageInput);
    }

    public LanguageDTO updateLanguage(Long id, LanguageInput languageInput) throws ExceptionGraphql {
        return languageService.updateLanguage(id, languageInput);
    }

    public Boolean deleteLanguage(Long id) throws ExceptionGraphql {
        languageService.deleteLanguage(id);
        return true;
    }
}
