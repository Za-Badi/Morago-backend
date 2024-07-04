package com.habsida.morago.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import com.habsida.morago.repository.CallRepository;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.CallServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CallServiceImplTest {

    @Mock
    private CallRepository callRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ThemeRepository themeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CallServiceImpl callService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void testCreateCall() {
        CreateCallInput input = new CreateCallInput();
        input.setTheme(1L);
        input.setCaller(1L);
        input.setRecipient(2L);

        User caller = new User();
        User recipient = new User();
        Theme theme = new Theme();

        when(userRepository.findById(1L)).thenReturn(Optional.of(caller));
        when(userRepository.findById(2L)).thenReturn(Optional.of(recipient));
        when(themeRepository.findById(1L)).thenReturn(Optional.of(theme));
        when(callRepository.save(any(Call.class))).thenReturn(new Call());
        when(modelMapper.map(any(Call.class), eq(CallDTO.class))).thenReturn(new CallDTO());

        CallDTO result = callService.createCall(input);

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
