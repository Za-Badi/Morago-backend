package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.LanguagePageOutput;
import com.habsida.morago.serviceImpl.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageQueryResolver implements GraphQLQueryResolver {
    private final LanguageService languageService;

    public LanguagePageOutput getAllLanguages(PagingInput pagingInput) {
        return languageService.getAllLanguages(pagingInput);
    }

    public LanguageDTO getLanguageById(Long id) throws ExceptionGraphql {
        return languageService.getLanguageById(id);
    }
}
