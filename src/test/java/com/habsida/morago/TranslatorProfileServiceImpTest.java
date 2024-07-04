package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.TranslatorProfileServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TranslatorProfileServiceImpTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TranslatorProfileServiceImp translatorProfileServiceImp;
    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testAddTranslatorProfile() throws ExceptionGraphql {
        TranslatorProfileDTO translatorProfileDTO = new TranslatorProfileDTO();
        User user = User.builder()
                .id(1L)
                .userProfile(null)
                .translatorProfile(null)
                .build();
        TranslatorProfileInput translatorProfileInput = new TranslatorProfileInput();
        translatorProfileInput.setDateOfBirth("1994-10-17");
        translatorProfileInput.setEmail("rixio@mail.com");
        translatorProfileInput.setIsAvailable(true);
        translatorProfileInput.setIsOnline(true);
        translatorProfileInput.setLevelOfKorean("Medium");
        translatorProfileInput.setLanguages(List.of(1L, 2L));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        when(languageRepository.findById(1L)).thenReturn(Optional.of(language1));
        when(languageRepository.findById(2L)).thenReturn(Optional.of(language2));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(TranslatorProfile.class), eq(TranslatorProfileDTO.class))).thenReturn(translatorProfileDTO);
        TranslatorProfileDTO result = translatorProfileServiceImp.addTranslatorProfile(1L, translatorProfileInput);
        assertNotNull(result);
        TranslatorProfile savedProfile = user.getTranslatorProfile();
        assertNotNull(savedProfile);
        assertEquals(translatorProfileInput.getDateOfBirth(), savedProfile.getDateOfBirth());
        assertEquals(translatorProfileInput.getEmail(), savedProfile.getEmail());
        assertEquals(translatorProfileInput.getIsAvailable(), savedProfile.getIsAvailable());
        assertEquals(translatorProfileInput.getIsOnline(), savedProfile.getIsOnline());
        assertEquals(translatorProfileInput.getLevelOfKorean(), savedProfile.getLevelOfKorean());
        assertEquals(2, savedProfile.getLanguages().size());
        assertTrue(savedProfile.getLanguages().contains(language1));
        assertTrue(savedProfile.getLanguages().contains(language2));
        verify(userRepository, times(1)).save(any(User.class));
    }
}