package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.CallNotFoundException;
import com.habsida.morago.exceptions.CallStatusNotFoundException;

import com.habsida.morago.exceptions.UserNotFoundException;
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
    public Call updateCall(Long id, CallStatus status, Integer duration, Float commission) throws Exception{
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new CallNotFoundException(id));

        if (status != null && !status.toString().isEmpty()) {
            call.setStatus(status);
        } else new Exception("status can't be null");


        if (duration != null && duration > 0) {
            call.setDuration(duration);
        } else if (duration != null && duration <= 0) {
            throw new Exception("Duration must be greater than zero.");
        }


        if (commission != null && commission > 0.0f) {
            call.setCommission(commission.doubleValue());
        } else if (commission != null && commission <= 0.0f) {
            throw new Exception("Commission must be greater than zero.");
        }
        call.setUpdatedAt(LocalDateTime.now());

        return callRepository.save(call);

    }


    @Override
    public void deleteCall(Long id) throws Exception {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new CallNotFoundException(id));
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


    @Override
    public List<Call> getAllMissedCalls( CallStatus status) throws Exception{

        if (status != CallStatus.MISSED_CALL) {
            throw new IllegalArgumentException("Invalid call status. Only MISSED_CALL is allowed.");
        }

        return callRepository.getCallsByMissedCall( status);
    }



//    @Override
//    public List<Call> getCallByStatus(CallStatus status) {
//        List<Call> calls = callRepository.findByStatus(status);
//        if (calls != null && !calls.isEmpty()) {
//            throw new CallStatusNotFoundException(status);
//        }
//        return calls;
//    }

    @Override
    public List<Call> getCallsByOutgoingIncoming() {
        return callRepository.getCallsByOutgoingIncoming();
    }

    @Override
    public List<Call> getCallsByOutgoingIncomingStatus(CallStatus callStatus) {
        if (callStatus == CallStatus.INCOMING_CALL || callStatus == CallStatus.OUTGOING_CALL) {
            return callRepository.getCallsByOutgoingIncomingStatus(callStatus);
        } else {
            throw new CallStatusNotFoundException(callStatus);
        }
    }


}
