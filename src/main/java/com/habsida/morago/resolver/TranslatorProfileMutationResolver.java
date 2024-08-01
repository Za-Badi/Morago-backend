package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.serviceImpl.TranslatorProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TranslatorProfileMutationResolver implements GraphQLMutationResolver {
    private final TranslatorProfileServiceImp translatorProfileServiceImp;

    public TranslatorProfileDTO addTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        return translatorProfileServiceImp.addTranslatorProfile(id, translatorProfileInput);
    }

    public TranslatorProfileDTO updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        return translatorProfileServiceImp.updateTranslatorProfile(id, translatorProfileInput);
    }

    public Boolean deleteTranslatorProfile(Long id) throws ExceptionGraphql {
        translatorProfileServiceImp.deleteTranslatorProfile(id);
        return true;
    }

    public TranslatorProfileDTO updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        return translatorProfileServiceImp.updateTranslatorProfileByUserId(id, translatorProfileInput);
    }

    public Boolean changeIsAvailable(Long id, Boolean isAvailable) throws ExceptionGraphql {
        translatorProfileServiceImp.changeIsAvailable(id, isAvailable);
        return true;
    }

    public Boolean changeIsOnline(Long id, Boolean isOnline) throws ExceptionGraphql {
        translatorProfileServiceImp.changeIsOnline(id, isOnline);
        return true;
    }

    public TranslatorProfileDTO addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws ExceptionGraphql {
        return translatorProfileServiceImp.addLanguageToTranslatorProfile(languageId, translatorProfileId);
    }

    public Boolean deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws ExceptionGraphql {
        translatorProfileServiceImp.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
        return true;
    }

    public UserDTO changeBalanceForTranslatorProfileId(Long id, Double balance) throws ExceptionGraphql {
        return translatorProfileServiceImp.changeBalanceForTranslatorProfileId(id, balance);
    }
}
