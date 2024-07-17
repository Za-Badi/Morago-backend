package com.habsida.morago.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.enums.UserStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import com.habsida.morago.repository.CallRepository;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DepositsService;
import com.habsida.morago.service.NotificationService;
import com.habsida.morago.serviceImpl.CallServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CallServiceImplTest {

    @Mock
    private CallRepository callRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ThemeRepository themeRepository;

    @Mock
    private NotificationService notificationService;

    @Mock
    private DepositsService depositsService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CallServiceImpl callService;

    private User user;
    private User translator;
    private Theme theme;
    private Call call;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("User");

        translator = new User();
        translator.setId(2L);
        translator.setFirstName("Translator");
        translator.setStatus(UserStatus.ONLINE);

        theme = new Theme();
        theme.setId(1L);
        theme.setPrice(BigDecimal.valueOf(10));

        call = new Call();
        call.setId(1L);
        call.setCaller(user);
        call.setRecipient(translator);
        call.setTheme(theme);
        call.setCreatedAt(LocalDateTime.now());
        call.setStatus(CallStatus.OUTGOING_CALL);
    }

    @Test
    public void testCreateCall() throws Exception {
        CreateCallInput input = new CreateCallInput("channel1", user.getId(), translator.getId(), theme.getId());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.findById(translator.getId())).thenReturn(Optional.of(translator));
        when(themeRepository.findById(theme.getId())).thenReturn(Optional.of(theme));
        when(callRepository.save(any(Call.class))).thenReturn(call);
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        CallDTO result = callService.createCall(input.getChannelName(), input.getCaller(), input.getRecipient(), input.getTheme());

        assertNotNull(result);
        verify(notificationService, times(1)).notifyCallCreation(translator, user);
        verify(userRepository, times(1)).save(translator);
    }

    @Test
    public void testEndCall() {
        when(callRepository.findById(call.getId())).thenReturn(Optional.of(call));
        when(themeRepository.findById(theme.getId())).thenReturn(Optional.of(theme));

        callService.endCall(call.getId(), CallStatus.COMPLETED, 30);

        verify(callRepository, times(1)).save(call);
        verify(depositsService, times(1)).addDeposit(any());
        verify(notificationService, times(1)).notifyCallEnd(user, translator, call);
        assertEquals(UserStatus.ONLINE, translator.getStatus());
    }

    @Test
    public void testRateCall() {
        when(callRepository.findById(call.getId())).thenReturn(Optional.of(call));
        when(userRepository.save(translator)).thenReturn(translator);

        callService.rateCall(user.getId(), call.getId(), 5.0);

        verify(callRepository, times(1)).save(call);
        verify(userRepository, times(1)).save(translator);
        assertTrue(call.getUserHasRated());
    }
    @Test
    void testGetAllCalls() {
        Call call = new Call();
        List<Call> calls = Arrays.asList(call);

        when(callRepository.findAll()).thenReturn(calls);
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        List<CallDTO> result = callService.getAllCalls();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetCallById() {
        Call call = new Call();
        when(callRepository.findById(1L)).thenReturn(Optional.of(call));
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        CallDTO result = callService.getCallById(1L);
        assertNotNull(result);
    }
    @Test
    void testUpdateCall() {
        Call call = new Call();
        call.setStatus(CallStatus.OUTGOING_CALL);
        when(callRepository.findById(1L)).thenReturn(Optional.of(call));
        when(callRepository.save(any(Call.class))).thenReturn(call);
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        CallDTO result = callService.updateCall(1L, CallStatus.OUTGOING_CALL, 10, 5.0f);

        assertNotNull(result);
        assertEquals(CallStatus.OUTGOING_CALL, call.getStatus());
    }

    @Test
    void testGetCallsByUserId() {
        Call call = new Call();
        List<Call> calls = Arrays.asList(call);
        when(callRepository.findCallsByUserId(1L)).thenReturn(calls);
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        List<CallDTO> result = callService.getCallsByUserId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllMissedCalls() {
        Call call = new Call();
        Page<Call> callPage = new PageImpl<>(Arrays.asList(call));
        Pageable pageable = PageRequest.of(0, 10);

        when(callRepository.getAllMissedCalls(1L, pageable)).thenReturn(callPage);
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        Page<CallDTO> result = callService.getAllMissedCalls(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void deleteCall() {
        doNothing().when(callRepository).deleteById(anyLong());
        callService.deleteCall(1L);
        verify(callRepository, times(1)).deleteById(1L);
    }
}
