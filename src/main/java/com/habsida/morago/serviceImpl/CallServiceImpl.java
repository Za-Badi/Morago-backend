package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Theme;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CallInput;
import com.habsida.morago.repository.CallRespository;
import com.habsida.morago.repository.ThemeRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.CallService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRespository callRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    @Override
    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    @Override
    public Call getCallById(Long id) {
        return callRepository.findById(id).orElseThrow(()-> new RuntimeException("Call with id " +id + (" not found")));
    }

    @Override
    public Call createCall(CallInput callInput){
        User caller = userRepository.findById(callInput.getCallerId()).orElseThrow(()-> new RuntimeException("caller not found"));
        User recipient = userRepository.findById(callInput.getRecipientId()).orElseThrow(()-> new RuntimeException("recipient not found"));
        Theme theme = themeRepository.findById(callInput.getThemeId()).orElseThrow(()-> new RuntimeException("theme not found"));
        Call call = new Call();
        call.setCaller(caller);
        call.setRecipient(recipient);
        call.setTheme(theme);

        return callRepository.save(call);
    }

    @Override
    @Transactional
    public Call updateCall(Long id, CallStatus status, Integer duration, Float commission) throws Exception{
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new Exception("Call not found for id: " + id));

        if (status != null) {
            call.setStatus(status);
        }


        if (duration != null) {
            call.setDuration(duration);
        }


        if (commission != null) {
            call.setCommission(Double.valueOf(commission));
        }

        return callRepository.save(call);

    }


    @Override
    public void deleteCall(Long id) throws Exception {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new Exception("Call not found for id: " + id));
        callRepository.delete(call);
    }


    @Override
    public List<Call> getCallByUserId(Long id) {
//        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        return callRepository.findByCallerId(id);
    }

    public List<Call> getAllMissedCall(CallStatus callStatus) {
        if (callStatus == CallStatus.MISSED_CALL) {
            return callRepository.findByStatus(callStatus);
        } else {
            return callRepository.findByStatus(callStatus);
        }
    }

    @Override
    public List<Call> getCallByStatus(CallStatus status) {
        return callRepository.findByStatus(status);
    }

}