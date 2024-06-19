//package com.habsida.morago.controllers;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.inputs.TranslatorPage;
//import com.habsida.morago.model.inputs.TranslatorProfileInput;
//import com.habsida.morago.model.entity.TranslatorProfile;
//import com.habsida.morago.resolver.TranslatorProfileResolver;
//import lombok.RequiredArgsConstructor;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class TranslatorProfileController {
//    private final TranslatorProfileResolver translatorProfileResolver;
//    @QueryMapping
//    public List<TranslatorProfile> getAllTranslatorProfiles() {return translatorProfileResolver.getAllTranslatorProfiles();}
//    @QueryMapping
//    public TranslatorPage getAllTranslatorProfilesPaged(@Argument Integer page, @Argument Integer size) {return translatorProfileResolver.getAllTranslatorProfilesPaged(page, size);}
//    @QueryMapping
//    public TranslatorProfile getTranslatorProfileById(@Argument Long id) throws GlobalException {
//        return translatorProfileResolver.getTranslatorProfileById(id);
//    }
//    @QueryMapping
//    public List<TranslatorProfile> getTranslatorProfilesByIsOnlineAndThemeId(@Argument Boolean isOnline, @Argument Long id) throws GlobalException {
//        return translatorProfileResolver.getTranslatorProfilesByIsOnlineAndThemeId(isOnline, id);
//    }
//    @QueryMapping
//    public TranslatorPage getTranslatorProfilesByIsOnlineAndThemeIdPaged(@Argument Integer page, @Argument Integer size, @Argument Boolean isOnline, @Argument Long id) {return translatorProfileResolver.getTranslatorProfilesByIsOnlineAndThemeIdPaged(page, size, id, isOnline);}
//    @QueryMapping
//    public List<TranslatorProfile> getTranslatorProfilesByThemeId(@Argument Long id) throws GlobalException {
//        return translatorProfileResolver.getTranslatorProfilesByThemeId(id);
//    }
//    @QueryMapping
//    public TranslatorPage getTranslatorProfilesByThemeIdPaged(@Argument Integer page, @Argument Integer size, @Argument Long id) {return translatorProfileResolver.getTranslatorProfilesByThemeIdPaged(page, size, id);}
//    @QueryMapping
//    public List<TranslatorProfile> getTranslatorProfilesByThemeName(@Argument String name) throws GlobalException {
//        return translatorProfileResolver.getTranslatorProfilesByThemeName(name);
//    }
//    @QueryMapping
//    public TranslatorPage getTranslatorProfilesByThemeNamePaged(@Argument Integer page, @Argument Integer size, @Argument String name) {return translatorProfileResolver.getTranslatorProfilesByThemeNamePaged(page, size, name);}
//    @MutationMapping
//    public TranslatorProfile addTranslatorProfile(@Argument Long id, @Argument TranslatorProfileInput translatorProfileInput) throws GlobalException {
//        return translatorProfileResolver.addTranslatorProfile(id, translatorProfileInput);
//    }
//    @MutationMapping
//    public TranslatorProfile updateTranslatorProfile(@Argument Long id, @Argument TranslatorProfileInput translatorProfileInput) throws GlobalException {
//        return translatorProfileResolver.updateTranslatorProfile(id, translatorProfileInput);
//    }
//    @MutationMapping
//    public Boolean deleteTranslatorProfile(@Argument Long id) throws GlobalException {
//        return translatorProfileResolver.deleteTranslatorProfile(id);
//    }
//    @MutationMapping
//    public TranslatorProfile updateTranslatorProfileByUserId(@Argument Long id, @Argument TranslatorProfileInput translatorProfileInput) throws GlobalException {
//        return translatorProfileResolver.updateTranslatorProfileByUserId(id, translatorProfileInput);
//    }
//    @MutationMapping
//    public TranslatorProfile changeIsAvailable(@Argument Long id, @Argument Boolean isAvailable) throws GlobalException {
//        return translatorProfileResolver.changeIsAvailable(id, isAvailable);
//    }
//    @MutationMapping
//    public TranslatorProfile changeIsOnline(@Argument Long id, @Argument Boolean isOnline) throws GlobalException {
//        return translatorProfileResolver.changeIsOnline(id, isOnline);
//    }
//    @MutationMapping
//    public TranslatorProfile addLanguageToTranslatorProfile(@Argument Long languageId, @Argument Long translatorProfileId) throws GlobalException {
//        return translatorProfileResolver.addLanguageToTranslatorProfile(languageId, translatorProfileId);
//    }
//    @MutationMapping
//    public Boolean deleteLanguageFromTranslatorProfile(@Argument Long languageId, @Argument Long translatorProfileId) throws GlobalException {
//        return translatorProfileResolver.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
//    }
//    @MutationMapping
//    public User changeBalanceForTranslatorProfileId(@Argument Long id, @Argument Float balance) throws GlobalException {
//        return translatorProfileResolver.changeBalanceForTranslatorProfileId(id, balance);
//    }
//}