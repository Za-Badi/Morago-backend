package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.serviceImpl.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanguageQueryResolver implements GraphQLQueryResolver {
    private final LanguageService languageService;
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    public Language getLanguageById(Long id) throws ExceptionGraphql {
        return languageService.getLanguageById(id);
    }
}
