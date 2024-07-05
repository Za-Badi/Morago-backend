package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.TranslatorProfileDTO;
import com.habsida.morago.model.dto.UserDTO;
import com.habsida.morago.model.entity.Language;
import com.habsida.morago.model.entity.TranslatorProfile;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.TranslatorPage;
import com.habsida.morago.model.inputs.TranslatorProfileInput;
import com.habsida.morago.repository.LanguageRepository;
import com.habsida.morago.repository.TranslatorProfileRepository;
import com.habsida.morago.repository.TranslatorProfileRepositoryPaged;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.TranslatorProfileServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class TranslatorProfileServiceImpTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TranslatorProfileRepository translatorProfileRepository;
    @Mock
    private TranslatorProfileRepositoryPaged translatorProfileRepositoryPaged;
    @InjectMocks
    private TranslatorProfileServiceImp translatorProfileServiceImp;
    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testGetAllTranslatorProfiles() {
        TranslatorProfile firstTranslator = new TranslatorProfile();
        firstTranslator.setId(1L);
        firstTranslator.setIsAvailable(true);
        TranslatorProfile secondTranslator = new TranslatorProfile();
        secondTranslator.setId(2L);
        secondTranslator.setIsAvailable(false);
        List<TranslatorProfile> profiles = Arrays.asList(firstTranslator, secondTranslator);
        TranslatorProfileDTO firstTranslatorDTO = new TranslatorProfileDTO();
        firstTranslatorDTO.setId(1L);
        firstTranslatorDTO.setIsAvailable(true);
        TranslatorProfileDTO secondTranslatorDTO = new TranslatorProfileDTO();
        secondTranslatorDTO.setId(2L);
        secondTranslatorDTO.setIsAvailable(false);
        List<TranslatorProfileDTO> expectedDTOs = Arrays.asList(firstTranslatorDTO, secondTranslatorDTO);
        when(translatorProfileRepository.findAll()).thenReturn(profiles);
        when(modelMapper.map(firstTranslator, TranslatorProfileDTO.class)).thenReturn(firstTranslatorDTO);
        when(modelMapper.map(secondTranslator, TranslatorProfileDTO.class)).thenReturn(secondTranslatorDTO);
        List<TranslatorProfileDTO> result = translatorProfileServiceImp.getAllTranslatorProfiles();
        assertNotNull(result);
        assertEquals(expectedDTOs.size(), result.size());
        assertEquals(expectedDTOs, result);
        verify(translatorProfileRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(firstTranslator, TranslatorProfileDTO.class);
        verify(modelMapper, times(1)).map(secondTranslator, TranslatorProfileDTO.class);
    }
    @Test
    public void testGetAllTranslatorProfilesPaged() {
        TranslatorProfile profile1 = TranslatorProfile.builder().isAvailable(true).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().isAvailable(false).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        Page<TranslatorProfile> profilePage = new PageImpl<>(profiles, PageRequest.of(0, 10), profiles.size());
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        List<TranslatorProfileDTO> profileDTOs = Arrays.asList(profileDTO1, profileDTO2);
        when(translatorProfileRepositoryPaged.findAll(PageRequest.of(0, 10))).thenReturn(profilePage);
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        TranslatorPage result = translatorProfileServiceImp.getAllTranslatorProfilesPaged(0, 10);
        assertNotNull(result);
        assertEquals(profileDTOs.size(), result.getContent().size());
        assertEquals(profilePage.getTotalPages(), result.getTotalPages());
        assertEquals((int) profilePage.getTotalElements(), result.getTotalElements());
        assertEquals(profilePage.getSize(), result.getSize());
        assertEquals(profilePage.getNumber(), result.getNumber());
        assertEquals(profileDTO1, result.getContent().get(0));
        assertEquals(profileDTO2, result.getContent().get(1));
        verify(translatorProfileRepositoryPaged, times(1)).findAll(PageRequest.of(0, 10));
    }
    @Test
    public void testGetTranslatorProfileById() throws ExceptionGraphql {
        Long profileId = 1L;
        TranslatorProfile translatorProfile = TranslatorProfile.builder().id(profileId).build();
        TranslatorProfileDTO translatorProfileDTO = new TranslatorProfileDTO();
        when(translatorProfileRepository.findById(profileId)).thenReturn(java.util.Optional.of(translatorProfile));
        when(modelMapper.map(translatorProfile, TranslatorProfileDTO.class)).thenReturn(translatorProfileDTO);
        TranslatorProfileDTO result = translatorProfileServiceImp.getTranslatorProfileById(profileId);
        assertNotNull(result);
        assertEquals(translatorProfileDTO, result);
        verify(translatorProfileRepository, times(1)).findById(profileId);
        verify(modelMapper, times(1)).map(translatorProfile, TranslatorProfileDTO.class);
    }
    @Test
    public void testGetTranslatorProfilesByIsOnlineAndThemeId() {
        Boolean isOnline = true;
        Long themeId = 1L;
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).isOnline(isOnline).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).isOnline(isOnline).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        when(translatorProfileRepository.findByIsOnlineAndThemesId(isOnline, themeId)).thenReturn(profiles);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        List<TranslatorProfileDTO> result = translatorProfileServiceImp.getTranslatorProfilesByIsOnlineAndThemeId(isOnline, themeId);
        assertNotNull(result);
        assertEquals(profiles.size(), result.size());
        assertEquals(profileDTO1, result.get(0));
        assertEquals(profileDTO2, result.get(1));
        verify(translatorProfileRepository, times(1)).findByIsOnlineAndThemesId(isOnline, themeId);
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
    }
    @Test
    public void testGetTranslatorProfilesByIsOnlineAndThemeIdPaged() {
        Long themeId = 1L;
        Boolean isOnline = true;
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).isOnline(isOnline).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).isOnline(isOnline).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        Page<TranslatorProfile> profilePage = new PageImpl<>(profiles, PageRequest.of(0, 10), profiles.size());
        when(translatorProfileRepositoryPaged.findByIsOnlineAndThemesId(isOnline, themeId, PageRequest.of(0, 10))).thenReturn(profilePage);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        TranslatorPage result = translatorProfileServiceImp.getTranslatorProfilesByIsOnlineAndThemeIdPaged(0, 10, themeId, isOnline);
        assertNotNull(result);
        assertEquals(profiles.size(), result.getContent().size());
        assertEquals(profilePage.getTotalPages(), result.getTotalPages());
        assertEquals((int) profilePage.getTotalElements(), result.getTotalElements());
        assertEquals(profilePage.getSize(), result.getSize());
        assertEquals(profilePage.getNumber(), result.getNumber());
        assertEquals(profileDTO1, result.getContent().get(0));
        assertEquals(profileDTO2, result.getContent().get(1));
        verify(translatorProfileRepositoryPaged, times(1)).findByIsOnlineAndThemesId(isOnline, themeId, PageRequest.of(0, 10));
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
    }
    @Test
    public void testGetTranslatorProfilesByThemeId() {
        Long themeId = 1L;
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        when(translatorProfileRepository.findByThemesId(themeId)).thenReturn(profiles);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        List<TranslatorProfileDTO> result = translatorProfileServiceImp.getTranslatorProfilesByThemeId(themeId);
        assertNotNull(result);
        assertEquals(profiles.size(), result.size());
        assertEquals(profileDTO1, result.get(0));
        assertEquals(profileDTO2, result.get(1));
        verify(translatorProfileRepository, times(1)).findByThemesId(themeId);
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
    }
    @Test
    public void testGetTranslatorProfilesByThemeIdPaged() {
        Long themeId = 1L;
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        Page<TranslatorProfile> profilePage = new PageImpl<>(profiles, PageRequest.of(0, 10), profiles.size());
        when(translatorProfileRepositoryPaged.findByThemesId(themeId, PageRequest.of(0, 10))).thenReturn(profilePage);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        TranslatorPage result = translatorProfileServiceImp.getTranslatorProfilesByThemeIdPaged(0, 10, themeId);
        assertNotNull(result);
        assertEquals(profiles.size(), result.getContent().size());
        assertEquals(profilePage.getTotalPages(), result.getTotalPages());
        assertEquals((int) profilePage.getTotalElements(), result.getTotalElements());
        assertEquals(profilePage.getSize(), result.getSize());
        assertEquals(profilePage.getNumber(), result.getNumber());
        assertEquals(profileDTO1, result.getContent().get(0));
        assertEquals(profileDTO2, result.getContent().get(1));
        verify(translatorProfileRepositoryPaged, times(1)).findByThemesId(themeId, PageRequest.of(0, 10));
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
    }
    @Test
    public void testGetTranslatorProfilesByThemeName() {
        String themeName = "themeName";
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        when(translatorProfileRepository.findByThemesName(themeName)).thenReturn(profiles);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        List<TranslatorProfileDTO> result = translatorProfileServiceImp.getTranslatorProfilesByThemeName(themeName);
        assertNotNull(result);
        assertEquals(profiles.size(), result.size());
        assertEquals(profileDTO1, result.get(0));
        assertEquals(profileDTO2, result.get(1));
        verify(translatorProfileRepository, times(1)).findByThemesName(themeName);
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
    }
    @Test
    public void testGetTranslatorProfilesByThemeNamePaged() {
        String themeName = "themeName";
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        Page<TranslatorProfile> profilePage = new PageImpl<>(profiles, PageRequest.of(0, 10), profiles.size());
        when(translatorProfileRepositoryPaged.findByThemesName(themeName, PageRequest.of(0, 10))).thenReturn(profilePage);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        TranslatorPage result = translatorProfileServiceImp.getTranslatorProfilesByThemeNamePaged(0, 10, themeName);
        assertNotNull(result);
        assertEquals(profiles.size(), result.getContent().size());
        assertEquals(profilePage.getTotalPages(), result.getTotalPages());
        assertEquals((int) profilePage.getTotalElements(), result.getTotalElements());
        assertEquals(profilePage.getSize(), result.getSize());
        assertEquals(profilePage.getNumber(), result.getNumber());
        assertEquals(profileDTO1, result.getContent().get(0));
        assertEquals(profileDTO2, result.getContent().get(1));
        verify(translatorProfileRepositoryPaged, times(1)).findByThemesName(themeName, PageRequest.of(0, 10));
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
    }
    @Test
    public void testSearchTranslators() {
        String searchInput = "rixio@mail.com";
        TranslatorProfile profile1 = TranslatorProfile.builder().id(1L).email("rixio@mail.com").build();
        TranslatorProfile profile2 = TranslatorProfile.builder().id(2L).email("ivana@mail.com").build();
        List<TranslatorProfile> profiles = Arrays.asList(profile1, profile2);
        Page<TranslatorProfile> profilePage = new PageImpl<>(profiles, PageRequest.of(0, 10), profiles.size());
        when(translatorProfileRepositoryPaged.searchTranslatorProfileByEmailOrLevelOfKoreanOrDateOfBirthOrLanguages(searchInput, PageRequest.of(0, 10))).thenReturn(profilePage);
        TranslatorProfileDTO profileDTO1 = new TranslatorProfileDTO();
        TranslatorProfileDTO profileDTO2 = new TranslatorProfileDTO();
        when(modelMapper.map(profile1, TranslatorProfileDTO.class)).thenReturn(profileDTO1);
        when(modelMapper.map(profile2, TranslatorProfileDTO.class)).thenReturn(profileDTO2);
        TranslatorPage result = translatorProfileServiceImp.searchTranslators(searchInput, 0, 10);
        assertNotNull(result);
        assertEquals(profiles.size(), result.getContent().size());
        assertEquals(profilePage.getTotalPages(), result.getTotalPages());
        assertEquals((int) profilePage.getTotalElements(), result.getTotalElements());
        assertEquals(profilePage.getSize(), result.getSize());
        assertEquals(profilePage.getNumber(), result.getNumber());
        assertEquals(profileDTO1, result.getContent().get(0));
        assertEquals(profileDTO2, result.getContent().get(1));
        verify(translatorProfileRepositoryPaged, times(1)).searchTranslatorProfileByEmailOrLevelOfKoreanOrDateOfBirthOrLanguages(searchInput, PageRequest.of(0, 10));
        verify(modelMapper, times(2)).map(any(), eq(TranslatorProfileDTO.class));
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
    @Test
    public void testUpdateTranslatorProfile() throws ExceptionGraphql {
        Language language1 = Language.builder().id(1L).name("English").build();
        Language language2 = Language.builder().id(2L).name("Spanish").build();
        Long profileId = 1L;
        TranslatorProfileInput input = new TranslatorProfileInput();
        input.setEmail("rixio@mail.com");
        input.setIsAvailable(true);
        input.setIsOnline(true);
        input.setLevelOfKorean("Medium");
        List<Long> languageIds = List.of(1L, 2L);
        input.setLanguages(languageIds);
        TranslatorProfile existingProfile = TranslatorProfile.builder()
                .id(profileId)
                .email("ivana@mail.com")
                .isAvailable(false)
                .isOnline(false)
                .levelOfKorean("Low")
                .languages(new ArrayList<>())
                .build();
        TranslatorProfile updatedProfile = TranslatorProfile.builder()
                .id(profileId)
                .email(input.getEmail())
                .isAvailable(input.getIsAvailable())
                .isOnline(input.getIsOnline())
                .levelOfKorean(input.getLevelOfKorean())
                .languages(List.of(language1, language2))
                .build();
        TranslatorProfileDTO expectedProfileDTO = new TranslatorProfileDTO();
        when(translatorProfileRepository.findById(profileId)).thenReturn(Optional.of(existingProfile));
        when(languageRepository.findById(1L)).thenReturn(Optional.of(language1));
        when(languageRepository.findById(2L)).thenReturn(Optional.of(language2));
        when(translatorProfileRepository.save(existingProfile)).thenReturn(updatedProfile);
        when(modelMapper.map(updatedProfile, TranslatorProfileDTO.class)).thenReturn(expectedProfileDTO);
        TranslatorProfileDTO result = translatorProfileServiceImp.updateTranslatorProfile(profileId, input);
        assertNotNull(result);
        assertEquals(expectedProfileDTO, result);
        assertEquals(input.getEmail(), updatedProfile.getEmail());
        assertEquals(input.getIsAvailable(), updatedProfile.getIsAvailable());
        assertEquals(input.getIsOnline(), updatedProfile.getIsOnline());
        assertEquals(input.getLevelOfKorean(), updatedProfile.getLevelOfKorean());
        assertEquals(languageIds.size(), updatedProfile.getLanguages().size());
        verify(translatorProfileRepository, times(1)).findById(profileId);
        verify(translatorProfileRepository, times(1)).save(existingProfile);
        verify(languageRepository, times(languageIds.size())).findById(anyLong());
        verify(modelMapper, times(1)).map(updatedProfile, TranslatorProfileDTO.class);
    }
    @Test
    public void testDeleteTranslatorProfile() throws ExceptionGraphql {
        Long profileId = 1L;
        User user = new User();
        user.setId(1L);
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .id(profileId)
                .user(user)
                .languages(new ArrayList<>())
                .themes(new ArrayList<>())
                .build();
        user.setTranslatorProfile(translatorProfile);
        when(translatorProfileRepository.findById(profileId)).thenReturn(Optional.of(translatorProfile));
        translatorProfileServiceImp.deleteTranslatorProfile(profileId);
        assertNull(user.getTranslatorProfile());
        verify(userRepository, times(1)).save(user);
        verify(translatorProfileRepository, times(1)).findById(profileId);
        verify(translatorProfileRepository, times(1)).save(translatorProfile);
        verify(translatorProfileRepository, times(1)).deleteById(profileId);
    }
    @Test
    public void testUpdateTranslatorProfileByUserId() throws ExceptionGraphql {
        Long userId = 1L;
        Long translatorProfileId = 1L;
        List<Long> languageIds = Arrays.asList(1L, 2L);
        TranslatorProfileInput translatorProfileInput = TranslatorProfileInput.builder()
                .dateOfBirth("1994-10-17")
                .email("rixio@mail.com")
                .isAvailable(true)
                .isOnline(true)
                .levelOfKorean("Advanced")
                .languages(languageIds)
                .build();
        Language language1 = Language.builder().id(1L).name("English").build();
        Language language2 = Language.builder().id(2L).name("Spanish").build();
        TranslatorProfile existingTranslatorProfile = TranslatorProfile.builder()
                .id(translatorProfileId)
                .dateOfBirth("1995-03-03")
                .email("ivana@mail.com")
                .isAvailable(false)
                .isOnline(false)
                .levelOfKorean("Intermediate")
                .languages(new ArrayList<>())
                .build();
        TranslatorProfile updatedTranslatorProfile = TranslatorProfile.builder()
                .id(translatorProfileId)
                .dateOfBirth(translatorProfileInput.getDateOfBirth())
                .email(translatorProfileInput.getEmail())
                .isAvailable(translatorProfileInput.getIsAvailable())
                .isOnline(translatorProfileInput.getIsOnline())
                .levelOfKorean(translatorProfileInput.getLevelOfKorean())
                .languages(Arrays.asList(language1, language2))
                .build();
        User user = User.builder()
                .id(userId)
                .translatorProfile(existingTranslatorProfile)
                .build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(translatorProfileRepository.findById(translatorProfileId)).thenReturn(Optional.of(existingTranslatorProfile));
        when(languageRepository.findById(1L)).thenReturn(Optional.of(language1));
        when(languageRepository.findById(2L)).thenReturn(Optional.of(language2));
        when(translatorProfileRepository.save(any(TranslatorProfile.class))).thenReturn(updatedTranslatorProfile);
        when(modelMapper.map(updatedTranslatorProfile, TranslatorProfileDTO.class)).thenReturn(TranslatorProfileDTO.builder()
                .id(updatedTranslatorProfile.getId())
                .dateOfBirth(updatedTranslatorProfile.getDateOfBirth())
                .email(updatedTranslatorProfile.getEmail())
                .isAvailable(updatedTranslatorProfile.getIsAvailable())
                .isOnline(updatedTranslatorProfile.getIsOnline())
                .levelOfKorean(updatedTranslatorProfile.getLevelOfKorean())
                .languages(Arrays.asList(language1.getName(), language2.getName()))
                .build());
        TranslatorProfileDTO updatedProfileDTO = translatorProfileServiceImp.updateTranslatorProfileByUserId(userId, translatorProfileInput);
        assertNotNull(updatedProfileDTO);
        assertEquals(updatedTranslatorProfile.getId(), updatedProfileDTO.getId());
        assertEquals(updatedTranslatorProfile.getDateOfBirth(), updatedProfileDTO.getDateOfBirth());
        assertEquals(updatedTranslatorProfile.getEmail(), updatedProfileDTO.getEmail());
        assertEquals(updatedTranslatorProfile.getIsAvailable(), updatedProfileDTO.getIsAvailable());
        assertEquals(updatedTranslatorProfile.getIsOnline(), updatedProfileDTO.getIsOnline());
        assertEquals(updatedTranslatorProfile.getLevelOfKorean(), updatedProfileDTO.getLevelOfKorean());
        assertEquals(languageIds.size(), updatedTranslatorProfile.getLanguages().size());
        verify(userRepository, times(1)).findById(userId);
        verify(translatorProfileRepository, times(1)).findById(translatorProfileId);
        verify(translatorProfileRepository, times(1)).save(any(TranslatorProfile.class));
        verify(languageRepository, times(languageIds.size())).findById(anyLong());
        verify(modelMapper, times(1)).map(updatedTranslatorProfile, TranslatorProfileDTO.class);
    }
    @Test
    public void testChangeIsAvailable() throws ExceptionGraphql {
        Long profileId = 1L;
        Boolean isAvailable = true;
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .id(profileId)
                .isAvailable(false)
                .build();
        when(translatorProfileRepository.findById(profileId))
                .thenReturn(Optional.of(translatorProfile));
        when(translatorProfileRepository.save(any(TranslatorProfile.class)))
                .thenReturn(translatorProfile);
        Boolean result = translatorProfileServiceImp.changeIsAvailable(profileId, isAvailable);
        assertTrue(result);
        assertEquals(isAvailable, translatorProfile.getIsAvailable());
        verify(translatorProfileRepository, times(1)).findById(profileId);
        verify(translatorProfileRepository, times(1)).save(translatorProfile);
    }
    @Test
    public void testChangeIsOnline() throws ExceptionGraphql {
        Long profileId = 1L;
        Boolean isOnline = true;
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .id(profileId)
                .isOnline(false)
                .build();
        when(translatorProfileRepository.findById(profileId))
                .thenReturn(Optional.of(translatorProfile));
        when(translatorProfileRepository.save(any(TranslatorProfile.class)))
                .thenReturn(translatorProfile);
        Boolean result = translatorProfileServiceImp.changeIsOnline(profileId, isOnline);
        assertTrue(result);
        assertEquals(isOnline, translatorProfile.getIsOnline());
        verify(translatorProfileRepository, times(1)).findById(profileId);
        verify(translatorProfileRepository, times(1)).save(translatorProfile);
    }
    @Test
    public void testAddLanguageToTranslatorProfile() throws ExceptionGraphql {
        Long translatorProfileId = 1L;
        Long languageId = 1L;
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .id(translatorProfileId)
                .languages(new ArrayList<>())
                .build();
        Language language = Language.builder()
                .id(languageId)
                .name("English")
                .build();
        when(translatorProfileRepository.findById(translatorProfileId))
                .thenReturn(Optional.of(translatorProfile));
        when(languageRepository.findById(languageId))
                .thenReturn(Optional.of(language));
        when(translatorProfileRepository.save(any(TranslatorProfile.class)))
                .thenReturn(translatorProfile);
        when(modelMapper.map(translatorProfile, TranslatorProfileDTO.class))
                .thenReturn(new TranslatorProfileDTO());
        TranslatorProfileDTO result = translatorProfileServiceImp.addLanguageToTranslatorProfile(languageId, translatorProfileId);
        assertNotNull(result);
        verify(translatorProfileRepository, times(1)).findById(translatorProfileId);
        verify(languageRepository, times(1)).findById(languageId);
        verify(translatorProfileRepository, times(1)).save(translatorProfile);
        verify(modelMapper, times(1)).map(translatorProfile, TranslatorProfileDTO.class);
    }
    @Test
    public void testDeleteLanguageFromTranslatorProfile() throws ExceptionGraphql {
        Long translatorProfileId = 1L;
        Long languageId = 1L;
        TranslatorProfile translatorProfile = TranslatorProfile.builder()
                .id(translatorProfileId)
                .languages(new ArrayList<>())
                .build();
        Language language = Language.builder()
                .id(languageId)
                .name("English")
                .build();
        translatorProfile.getLanguages().add(language);
        when(translatorProfileRepository.findById(translatorProfileId))
                .thenReturn(Optional.of(translatorProfile));
        when(languageRepository.findById(languageId))
                .thenReturn(Optional.of(language));
        translatorProfileServiceImp.deleteLanguageFromTranslatorProfile(languageId, translatorProfileId);
        verify(translatorProfileRepository, times(1)).findById(translatorProfileId);
        verify(languageRepository, times(1)).findById(languageId);
        verify(translatorProfileRepository, times(1)).save(translatorProfile);
    }
    @Test
    public void testChangeBalanceForTranslatorProfileId() throws ExceptionGraphql {
        Long profileId = 1L;
        Double newBalance = 1000.0;
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setId(profileId);
        User user = new User();
        user.setId(2L);
        user.setBalance(500.0);
        translatorProfile.setUser(user);
        when(translatorProfileRepository.findById(profileId)).thenReturn(Optional.of(translatorProfile));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        user.setBalance(newBalance);
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(user.getId());
        expectedUserDTO.setBalance(newBalance);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(expectedUserDTO);
        UserDTO result = translatorProfileServiceImp.changeBalanceForTranslatorProfileId(profileId, newBalance);
        assertNotNull(result);
        assertEquals(expectedUserDTO.getId(), result.getId());
        assertEquals(expectedUserDTO.getBalance(), result.getBalance());
        verify(translatorProfileRepository, times(1)).findById(profileId);
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).save(any(User.class));
        verify(modelMapper, times(1)).map(user, UserDTO.class);
    }
}