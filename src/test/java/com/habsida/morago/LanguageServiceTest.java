package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.LanguageDTO;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.enums.ESort;
import com.habsida.morago.model.inputs.LanguageInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.LanguagePageOutput;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.serviceImpl.LanguageService;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;


    @Mock
    private ModelMapperUtil modelMapperUtil;

    @InjectMocks
    private LanguageService languageService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllLanguages() {
        Language english = Language.builder().id(1L).name("Spanish").build();
        Language korean = Language.builder().id(2L).name("Korean").build();
        List<Language> languages = Arrays.asList(english, korean);
        LanguageDTO englishDTO = new LanguageDTO();
        englishDTO.setId(1L);
        englishDTO.setName("English");
        LanguageDTO koreanDTO = new LanguageDTO();
        koreanDTO.setId(2L);
        koreanDTO.setName("Korean");
        List<LanguageDTO> expectedLanguageDTOs = Arrays.asList(englishDTO, koreanDTO);

        PagingInput pagingInput = new PagingInput(0, 10, "id", ESort.DES);
        Pageable pageable = PageUtil.buildPageable(pagingInput);
        Page<Language> languagePage = new PageImpl<>(languages, pageable, languages.size());

        when(languageRepository.findAll(pageable)).thenReturn(languagePage);
        when(modelMapperUtil.map(english, LanguageDTO.class)).thenReturn(englishDTO);
        when(modelMapperUtil.map(korean, LanguageDTO.class)).thenReturn(koreanDTO);

        LanguagePageOutput result = languageService.getAllLanguages(pagingInput);
        assertNotNull(result);
        assertEquals(expectedLanguageDTOs.size(), result.getObjectList().size());
        assertEquals(expectedLanguageDTOs, result.getObjectList());
        verify(languageRepository, times(1)).findAll(pageable);
        verify(modelMapperUtil, times(1)).map(english, LanguageDTO.class);
        verify(modelMapperUtil, times(1)).map(korean, LanguageDTO.class);
    }

    @Test
    public void testGetLanguageById() throws ExceptionGraphql {
        Long languageId = 1L;
        String languageName = "English";
        Language language = new Language();
        language.setId(languageId);
        language.setName(languageName);
        LanguageDTO expectedLanguageDTO = new LanguageDTO();
        expectedLanguageDTO.setId(languageId);
        expectedLanguageDTO.setName(languageName);

        when(languageRepository.findById(languageId)).thenReturn(java.util.Optional.of(language));
        when(modelMapperUtil.map(language, LanguageDTO.class)).thenReturn(expectedLanguageDTO);

        LanguageDTO result = languageService.getLanguageById(languageId);
        assertNotNull(result);
        assertEquals(expectedLanguageDTO.getId(), result.getId());
        assertEquals(expectedLanguageDTO.getName(), result.getName());
        verify(languageRepository, times(1)).findById(languageId);
        verify(modelMapperUtil, times(1)).map(language, LanguageDTO.class);
    }

    @Test
    public void testAddLanguage() {
        LanguageInput languageInput = new LanguageInput();
        languageInput.setName("English");
        Language language = new Language();
        language.setName("English");

        when(languageRepository.save(any(Language.class))).thenReturn(language);
        LanguageDTO languageDTO = new LanguageDTO();
        when(modelMapperUtil.map(any(Language.class), eq(LanguageDTO.class))).thenReturn(languageDTO);

        LanguageDTO result = languageService.addLanguage(languageInput);
        assertNotNull(result);
        assertEquals(languageInput.getName(), language.getName());
        verify(languageRepository, times(1)).save(any(Language.class));
    }

    @Test
    public void testUpdateLanguage() throws ExceptionGraphql {
        Long languageId = 1L;
        String firstName = "AmericanEnglish";
        String secondName = "BritishEnglish";
        Language language = new Language();
        language.setId(languageId);
        language.setName(firstName);
        LanguageInput languageInput = new LanguageInput();
        languageInput.setName(secondName);
        Language updatedLanguage = new Language();
        updatedLanguage.setId(languageId);
        updatedLanguage.setName(secondName);
        LanguageDTO expectedLanguageDTO = new LanguageDTO();
        expectedLanguageDTO.setId(languageId);
        expectedLanguageDTO.setName(secondName);

        when(languageRepository.findById(languageId)).thenReturn(java.util.Optional.of(language));
        when(languageRepository.save(any(Language.class))).thenReturn(updatedLanguage);
        when(modelMapperUtil.map(updatedLanguage, LanguageDTO.class)).thenReturn(expectedLanguageDTO);

        LanguageDTO result = languageService.updateLanguage(languageId, languageInput);
        assertNotNull(result);
        assertEquals(expectedLanguageDTO.getId(), result.getId());
        assertEquals(expectedLanguageDTO.getName(), result.getName());
        verify(languageRepository, times(1)).findById(languageId);
        verify(languageRepository, times(1)).save(any(Language.class));
        verify(modelMapperUtil, times(1)).map(updatedLanguage, LanguageDTO.class);
    }

    @Test
    public void testDeleteLanguage() throws ExceptionGraphql {
        Long languageId = 1L;
        String languageName = "English";
        Language languageToDelete = new Language();
        languageToDelete.setId(languageId);
        languageToDelete.setName(languageName);

        when(languageRepository.findById(languageId)).thenReturn(Optional.of(languageToDelete));
        doNothing().when(languageRepository).delete(languageToDelete);

        languageService.deleteLanguage(languageId);
        verify(languageRepository, times(1)).findById(languageId);
        verify(languageRepository, times(1)).delete(languageToDelete);
    }
}
