package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import com.habsida.morago.model.enums.ESort;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.TranslatorPage;
import com.habsida.morago.serviceImpl.TranslatorProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TranslatorProfileQueryResolver implements GraphQLQueryResolver {
    private final TranslatorProfileServiceImp translatorProfileServiceImp;


    public TranslatorPage getAllTranslatorProfilesPaged(Integer page, Integer size) {
        PagingInput input = new PagingInput(page, size, "id", ESort.DES); // Adjust sort parameters as needed
        return translatorProfileServiceImp.getAllTranslatorProfilesPaged(input);
    }

    public TranslatorProfileDTO getTranslatorProfileById(Long id) throws ExceptionGraphql {
        return translatorProfileServiceImp.getTranslatorProfileById(id);
    }


    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(Integer page, Integer size, Long id, Boolean isOnline) {
        PagingInput input = new PagingInput(page, size, "id", ESort.DES); // Adjust sort parameters as needed
        return translatorProfileServiceImp.getTranslatorProfilesByIsOnlineAndThemeIdPaged(input, id, isOnline);
    }


    public TranslatorPage getTranslatorProfilesByThemeIdPaged(Integer page, Integer size, Long id) {
        PagingInput input = new PagingInput(page, size, "id", ESort.DES); // Adjust sort parameters as needed
        return translatorProfileServiceImp.getTranslatorProfilesByThemeIdPaged(input, id);
    }


    public TranslatorPage getTranslatorProfilesByThemeNamePaged(Integer page, Integer size, String name) {
        PagingInput input = new PagingInput(page, size, "id", ESort.DES); // Adjust sort parameters as needed
        return translatorProfileServiceImp.getTranslatorProfilesByThemeNamePaged(input, name);
    }

    public TranslatorPage searchTranslators(String searchInput, Integer page, Integer size) {
        PagingInput input = new PagingInput(page, size, "id", ESort.DES); // Adjust sort parameters as needed
        return translatorProfileServiceImp.searchTranslators(searchInput, input);
    }
}
