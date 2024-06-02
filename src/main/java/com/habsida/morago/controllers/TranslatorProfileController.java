package com.habsida.morago.controllers;

import com.habsida.morago.dtos.TranslatorProfileInput;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.resolvers.TranslatorProfileResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TranslatorProfileController {
    private final TranslatorProfileResolver translatorProfileResolver;
    public TranslatorProfileController(TranslatorProfileResolver translatorProfileResolver) {this.translatorProfileResolver = translatorProfileResolver;}
    @QueryMapping
    public List<TranslatorProfile> getAllTranslatorProfiles() {return translatorProfileResolver.getAllTranslatorProfiles();}
    @QueryMapping
    public TranslatorProfile getTranslatorProfileById(@Argument Long id) throws Exception {
        return translatorProfileResolver.getTranslatorProfileById(id);
    }
    @MutationMapping
    public TranslatorProfile addTranslatorProfile(@Argument TranslatorProfileInput translatorProfileDto) {
        return translatorProfileResolver.addTranslatorProfile(translatorProfileDto);
    }
    @MutationMapping
    public TranslatorProfile updateTranslatorProfile(@Argument Long id, @Argument TranslatorProfileInput translatorProfileDto) throws Exception {
        return translatorProfileResolver.updateTranslatorProfile(id, translatorProfileDto);
    }
    @MutationMapping
    public Boolean deleteTranslatorProfile(@Argument Long id) throws Exception {
        return translatorProfileResolver.deleteTranslatorProfile(id);
    }
}
