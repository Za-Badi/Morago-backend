package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.serviceImpl.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private LanguageService languageService;
    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testAddLanguage() {
        LanguageInput languageInput = new LanguageInput();
        languageInput.setName("English");
        Language language = new Language();
        language.setName("English");
        when(languageRepository.save(any(Language.class))).thenReturn(language);
        LanguageDTO languageDTO = new LanguageDTO();
        when(modelMapper.map(any(Language.class), eq(LanguageDTO.class))).thenReturn(languageDTO);
        LanguageDTO result = languageService.addLanguage(languageInput);
        assertNotNull(result);
        assertEquals(languageInput.getName(), language.getName());
        verify(languageRepository, times(1)).save(any(Language.class));
    }
}