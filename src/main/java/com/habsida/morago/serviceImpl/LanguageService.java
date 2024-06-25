package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(language -> modelMapper.map(language, LanguageDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LanguageDTO getLanguageById(Long id) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        return modelMapper.map(language, LanguageDTO.class);
    }

    @Transactional
    public LanguageDTO addLanguage(LanguageInput languageInput) {
        Language language = new Language();
        language.setName(languageInput.getName());
        Language savedLanguage = languageRepository.save(language);
        return modelMapper.map(savedLanguage, LanguageDTO.class);
    }

    @Transactional
    public LanguageDTO updateLanguage(Long id, LanguageInput languageInput) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        if (languageInput.getName() != null && !languageInput.getName().isBlank()) {
            language.setName(languageInput.getName());
        }
        Language updatedLanguage = languageRepository.save(language);
        return modelMapper.map(updatedLanguage, LanguageDTO.class);
    }

    @Transactional
    public void deleteLanguage(Long id) throws ExceptionGraphql {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ExceptionGraphql("Language not found with id: " + id));
        languageRepository.delete(language);
    }
}
