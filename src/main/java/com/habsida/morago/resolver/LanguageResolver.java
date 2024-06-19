//package com.habsida.morago.resolver;
//
//import com.habsida.morago.exceptions.GlobalException;
//import com.habsida.morago.model.inputs.LanguageInput;
//import com.habsida.morago.model.entity.Language;
//import com.habsida.morago.service.LanguageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class LanguageResolver {
//    private final LanguageService languageService;
//
//    public List<Language> getAllLanguages() {
//        return languageService.getAllLanguages();
//    }
//
//    public Language getLanguageById(Long id) throws GlobalException {
//        return languageService.getLanguageById(id);
//    }
//
//    public Language addLanguage(LanguageInput languageInput) {
//        return languageService.addLanguage(languageInput);
//    }
//
//    public Language updateLanguage(Long id, LanguageInput languageInput) throws GlobalException {
//        return languageService.updateLanguage(id, languageInput);
//    }
//
//    public Boolean deleteLanguage(Long id) throws GlobalException {
//        languageService.deleteLanguage(id);
//        return true;
//    }
//}