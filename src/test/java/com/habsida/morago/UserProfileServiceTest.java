package com.habsida.morago;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.habsida.morago.exceptions.ExceptionGraphql;
import com.habsida.morago.model.dto.UserProfileDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.UserProfile;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private UserProfileService userProfileService;
    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testAddUserProfile() throws ExceptionGraphql {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        User user = User.builder()
                .id(1L)
                .userProfile(null)
                .translatorProfile(null)
                .build();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(UserProfile.class), eq(UserProfileDTO.class))).thenReturn(userProfileDTO);
        UserProfileDTO result = userProfileService.addUserProfile(true, 1L);
        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}