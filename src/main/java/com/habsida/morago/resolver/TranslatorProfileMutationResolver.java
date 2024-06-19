package com.habsida.morago.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.serviceImpl.TranslatorProfileServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TranslatorProfileMutationResolver implements GraphQLMutationResolver {
    private final TranslatorProfileServiceImp translatorProfileServiceImp;
    public TranslatorProfile addTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        return translatorProfileServiceImp.addTranslatorProfile(id, translatorProfileInput);
    }
    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        return translatorProfileServiceImp.updateTranslatorProfile(id, translatorProfileInput);
    }
    public Boolean deleteTranslatorProfile(Long id) throws ExceptionGraphql {
        translatorProfileServiceImp.deleteTranslatorProfile(id);
        return true;
    }
    public TranslatorProfile updateTranslatorProfileByUserId(Long id, TranslatorProfileInput translatorProfileInput) throws ExceptionGraphql {
        return translatorProfileServiceImp.updateTranslatorProfileByUserId(id, translatorProfileInput);
    }
    public Boolean changeIsAvailable(Long id, Boolean isAvailable) throws ExceptionGraphql {
        return translatorProfileServiceImp.changeIsAvailable(id, isAvailable);
    }
    public Boolean changeIsOnline(Long id, Boolean isOnline) throws ExceptionGraphql {
        return translatorProfileServiceImp.changeIsOnline(id, isOnline);
    }
    public TranslatorProfile addLanguageToTranslatorProfile(Long languageId, Long translatorProfileId) throws ExceptionGraphql {
        return translatorProfileServiceImp.addLanguageToTranslatorProfile(languageId, translatorProfileId);
    }
    public Boolean deleteLanguageFromTranslatorProfile(Long languageId, Long translatorProfileId) throws ExceptionGraphql {
        translatorProfileServiceImp.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
        return true;
    }
    public User changeBalanceForTranslatorProfileId(Long id, Double balance) throws ExceptionGraphql {
        return translatorProfileServiceImp.changeBalanceForTranslatorProfileId(id, balance);
    }
}
