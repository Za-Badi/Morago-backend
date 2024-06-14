//package com.habsida.morago.controllers;
//
//import com.habsida.morago.model.entity.User;
//import com.habsida.morago.model.inputs.TranslatorProfileInput;
//import com.habsida.morago.model.entity.TranslatorProfile;
//import com.habsida.morago.resolver.TranslatorProfileResolver;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//public class TranslatorProfileController {
//    private final TranslatorProfileResolver translatorProfileResolver;
//    public TranslatorProfileController(TranslatorProfileResolver translatorProfileResolver) {this.translatorProfileResolver = translatorProfileResolver;}
//    @QueryMapping
//    public List<TranslatorProfile> getAllTranslatorProfiles() {return translatorProfileResolver.getAllTranslatorProfiles();}
//    @QueryMapping
//    public TranslatorProfile getTranslatorProfileById(@Argument Long id) throws Exception {
//        return translatorProfileResolver.getTranslatorProfileById(id);
//    }
//    @QueryMapping
//    public List<TranslatorProfile> getTranslatorProfilesByIsOnline(@Argument Boolean isOnline) throws Exception {
//        return translatorProfileResolver.getTranslatorProfilesByIsOnline(isOnline);
//    }
//    @QueryMapping
//    public List<TranslatorProfile> getTranslatorProfilesByThemeId(@Argument Long id) throws Exception {
//        return translatorProfileResolver.getTranslatorProfilesByThemeId(id);
//    }
//    @QueryMapping
//    public List<TranslatorProfile> getTranslatorProfilesByThemeName(@Argument String name) throws Exception {
//        return translatorProfileResolver.getTranslatorProfilesByThemeName(name);
//    }
//
//    @MutationMapping
//    public TranslatorProfile addTranslatorProfile(@Argument TranslatorProfileInput translatorProfileInput) throws Exception {
//        return translatorProfileResolver.addTranslatorProfile(translatorProfileInput);
//    }
//    @MutationMapping
//    public TranslatorProfile updateTranslatorProfile(@Argument Long id, @Argument TranslatorProfileInput translatorProfileInput) throws Exception {
//        return translatorProfileResolver.updateTranslatorProfile(id, translatorProfileInput);
//    }
//    @MutationMapping
//    public Boolean deleteTranslatorProfile(@Argument Long id) throws Exception {
//        return translatorProfileResolver.deleteTranslatorProfile(id);
//    }
//    @MutationMapping
//    public TranslatorProfile updateTranslatorProfileByUserId(@Argument Long id, @Argument TranslatorProfileInput translatorProfileInput) throws Exception {
//        return translatorProfileResolver.updateTranslatorProfileByUserId(id, translatorProfileInput);
//    }
//    @MutationMapping
//    public Boolean changeIsAvailable(@Argument Long id) throws Exception {
//        return translatorProfileResolver.changeIsAvailable(id);
//    }
//    @MutationMapping
//    public Boolean changeIsOnline(@Argument Long id) throws Exception {
//        return translatorProfileResolver.changeIsOnline(id);
//    }
//    @MutationMapping
//    public TranslatorProfile addLanguageToTranslatorProfile(@Argument Long languageId, @Argument Long translatorProfileId) throws Exception {
//        return translatorProfileResolver.addLanguageToTranslatorProfile(languageId, translatorProfileId);
//    }
//    @MutationMapping
//    public Boolean deleteLanguageFromTranslatorProfile(@Argument Long languageId, @Argument Long translatorProfileId) throws Exception {
//        return translatorProfileResolver.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
//    }
//    @MutationMapping
//    public User changeBalanceForTranslatorProfileId(@Argument Long id, @Argument Float balance) throws Exception {
//        return translatorProfileResolver.changeBalanceForTranslatorProfileId(id, balance);
//    }
//}