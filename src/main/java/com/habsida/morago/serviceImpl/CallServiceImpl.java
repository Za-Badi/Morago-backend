package com.habsida.morago.serviceImpl;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    @Override
    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }
    public List<Call> getAllFreeCall(){
        return callRepository.getFreeCallIsMade();
    }

    @Override
    public Call getCallById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        return callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("call not found " + id));
    }
    @Override
    public Call createCall(CreateCallInput input){
        Long themeId = input.getTheme();
        Long callerId = input.getCaller();
        Long recipientId = input.getRecipient();

        if (themeId == null || callerId == null || recipientId == null) {
            throw new IllegalArgumentException("ThemeId, callerId, and recipientId must not be null.");
        }

        User caller = userRepository.findById(callerId).orElseThrow(()-> new IllegalArgumentException("user not found" +input.getCaller()));
        User recipient = userRepository.findById(recipientId).orElseThrow(()-> new IllegalArgumentException("recipient not found" +input.getRecipient()));
        Theme theme = themeRepository.findById(themeId).orElseThrow(()-> new IllegalArgumentException("theme not found " +input.getTheme()));

        Call call = new Call();
        call.setCaller(caller);
        call.setRecipient(recipient);
        call.setTheme(theme);
        call.setCreatedAt(LocalDateTime.now());

        return callRepository.save(call);
    }

    @Override
    @Transactional
    public Call updateCall(Long id, CallStatus status, Integer duration, Float commission){
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calls not found"));

        if (status != null && !status.toString().isEmpty()) {
            call.setStatus(status);
        } else throw new IllegalArgumentException("status can't be null");


        if (duration != null) {
            if (duration == 0) {
                throw new IllegalArgumentException("Duration must be greater than zero.");
            } else if (duration > 0) {
                call.setDuration(duration);
            }
        } else {
            throw new IllegalArgumentException("Duration cannot be null.");
        }


        if (commission != null && commission >= 0.0f) {
            call.setCommission(commission.doubleValue());
        } else if (commission != null && commission <= 0.0f) {
            throw new IllegalArgumentException("Commission must be greater than zero.");
        }
        call.setUpdatedAt(LocalDateTime.now());

        return callRepository.save(call);

    }
    @Override
    public void deleteCall(Long id)  {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calls not found"));
        callRepository.delete(call);
    }


    @Override
    public List<Call> getCallsByUserId(Long userId) {
        List<Call> calls = callRepository.findCallsByUserId(userId);

        if (calls == null || calls.isEmpty()) {
            throw new IllegalArgumentException("user not found");
        }
        return calls;
    }

    public Page<Call> getAllMissedCalls(Long userId, Pageable pageable) {
        return callRepository.getAllMissedCalls(userId, pageable);
    }

    public Page<Call> getCallsByOutgoingIncomingStatus(CallStatus status, Pageable pageable) {
        return callRepository.getCallsByOutgoingIncomingStatus(status, pageable);
    }

    public Page<Call> getCallsByOutgoingIncoming(Pageable pageable) {
        return callRepository.getCallsByOutgoingIncoming(pageable);
    }


}
