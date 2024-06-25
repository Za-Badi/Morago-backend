package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import com.habsida.morago.model.inputs.TranslatorPage;
import com.habsida.morago.serviceImpl.TranslatorProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TranslatorProfileQueryResolver implements GraphQLQueryResolver {
    private final TranslatorProfileServiceImp translatorProfileServiceImp;

    public List<TranslatorProfileDTO> getAllTranslatorProfiles() {
        return translatorProfileServiceImp.getAllTranslatorProfiles();
    }

    public TranslatorPage getAllTranslatorProfilesPaged(Integer page, Integer size) {
        return translatorProfileServiceImp.getAllTranslatorProfilesPaged(page, size);
    }

    public TranslatorProfileDTO getTranslatorProfileById(Long id) throws ExceptionGraphql {
        return translatorProfileServiceImp.getTranslatorProfileById(id);
    }

    public List<TranslatorProfileDTO> getTranslatorProfilesByIsOnlineAndThemeId(Boolean isOnline, Long id) {
        return translatorProfileServiceImp.getTranslatorProfilesByIsOnlineAndThemeId(isOnline, id);
    }

    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(Integer page, Integer size, Long id, Boolean isOnline) {
        return translatorProfileServiceImp.getTranslatorProfilesByIsOnlineAndThemeIdPaged(page, size, id, isOnline);
    }

    public List<TranslatorProfileDTO> getTranslatorProfilesByThemeId(Long id) {
        return translatorProfileServiceImp.getTranslatorProfilesByThemeId(id);
    }

    public TranslatorPage getTranslatorProfilesByThemeIdPaged(Integer page, Integer size, Long id) {
        return translatorProfileServiceImp.getTranslatorProfilesByThemeIdPaged(page, size, id);
    }

    public List<TranslatorProfileDTO> getTranslatorProfilesByThemeName(String name) {
        return translatorProfileServiceImp.getTranslatorProfilesByThemeName(name);
    }

    public TranslatorPage getTranslatorProfilesByThemeNamePaged(Integer page, Integer size, String name) {
        return translatorProfileServiceImp.getTranslatorProfilesByThemeNamePaged(page, size, name);
    }

    public TranslatorPage searchTranslators(String searchInput, Integer page, Integer size) {
        return translatorProfileServiceImp.searchTranslators(searchInput, page, size);
    }
}
