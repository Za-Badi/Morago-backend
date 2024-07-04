package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import com.habsida.morago.repository.CallRepository;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<CallDTO> getAllCalls() {
        List<Call> calls = callRepository.findAll();
        return calls.stream()
                .map(call -> modelMapper.map(call, CallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CallDTO> getAllFreeCall() {
        List<Call> calls = callRepository.getFreeCallIsMade();
        return calls.stream()
                .map(call -> modelMapper.map(call, CallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CallDTO getCallById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + id));
        return modelMapper.map(call, CallDTO.class);
    }

    @Override
    @Transactional
    public CallDTO createCall(CreateCallInput input) {
        Long themeId = input.getTheme();
        Long callerId = input.getCaller();
        Long recipientId = input.getRecipient();

        if (themeId == null || callerId == null || recipientId == null) {
            throw new IllegalArgumentException("ThemeId, callerId, and recipientId must not be null.");
        }

        User caller = userRepository.findById(callerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + callerId));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found with id: " + recipientId));
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new IllegalArgumentException("Theme not found with id: " + themeId));

        Call call = new Call();
        call.setCaller(caller);
        call.setRecipient(recipient);
        call.setTheme(theme);
        call.setCreatedAt(LocalDateTime.now());

        Call savedCall = callRepository.save(call);
        return modelMapper.map(savedCall, CallDTO.class);
    }

    @Override
    @Transactional
    public CallDTO updateCall(Long id, CallStatus status, Integer duration, Float commission) {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Call not found with id: " + id));

        if (status != null && !status.toString().isEmpty()) {
            call.setStatus(status);
        } else {
            throw new IllegalArgumentException("Status can't be null or empty");
        }

        if (duration != null) {
            if (duration == 0) {
                throw new IllegalArgumentException("Duration must be greater than zero.");
            } else {
                call.setDuration(duration);
            }
        } else {
            throw new IllegalArgumentException("Duration cannot be null.");
        }

        if (commission != null) {
            if (commission >= 0.0f) {
                call.setCommission(commission.doubleValue());
            } else {
                throw new IllegalArgumentException("Commission must be greater than zero.");
            }
        }
        call.setUpdatedAt(LocalDateTime.now());

        Call updatedCall = callRepository.save(call);
        return modelMapper.map(updatedCall, CallDTO.class);
    }

    @Override
    @Transactional
    public void deleteCall(Long id) {
        callRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CallDTO> getCallsByUserId(Long userId) {
        List<Call> calls = callRepository.findCallsByUserId(userId);
        if (calls == null || calls.isEmpty()) {
            throw new IllegalArgumentException("Calls not found for user with id: " + userId);
        }
        return calls.stream()
                .map(call -> modelMapper.map(call, CallDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Page<CallDTO> getAllMissedCalls(Long userId, Pageable pageable) {
        return callRepository.getAllMissedCalls(userId, pageable)
                .map(call -> modelMapper.map(call, CallDTO.class));
    }

    @Override
    @Transactional
    public Page<CallDTO> getCallsByOutgoingIncomingStatus(CallStatus status, Pageable pageable) {
        return callRepository.getCallsByOutgoingIncomingStatus(status, pageable)
                .map(call -> modelMapper.map(call, CallDTO.class));
    }

    @Override
    @Transactional
    public Page<CallDTO> getCallsByOutgoingIncoming(Pageable pageable) {
        return callRepository.getCallsByOutgoingIncoming(pageable)
                .map(call -> modelMapper.map(call, CallDTO.class));
    }
}
