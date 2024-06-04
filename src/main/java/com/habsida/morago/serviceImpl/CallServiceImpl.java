package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Call;

import com.habsida.morago.repository.CallRespository;
import com.habsida.morago.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {


    private final CallRespository callRespository;


    @Override
    public List<Call> getAllCalls() {
        return callRespository.findAll();
    }

    @Override

    public Call getCallsById(Long id) {
        return callRespository.findById(id).orElseThrow(()->new RuntimeException("call not found with id " +id));
    }

    @Override
    public Call createCall(Call call) {
        return callRespository.save(call);
    }

    @Override
    public Call updateCalls(Long id, Call callsDetails) {
        Call existingCall = callRespository.findById(id).orElseThrow(() -> new RuntimeException("call not found with id " + id));
        existingCall.setCaller(callsDetails.getCaller());
        existingCall.setRecipient(callsDetails.getRecipient());
        existingCall.setCreatedAt(callsDetails.getCreatedAt());
        existingCall.setDuration(callsDetails.getDuration());
        existingCall.setStatus(callsDetails.getStatus());
        existingCall.setSum(callsDetails.getSum());
        existingCall.setCommission(callsDetails.getCommission());
        existingCall.setTranslatorHasRated(callsDetails.getTranslatorHasRated());
        existingCall.setUserHasRated(callsDetails.getUserHasRated());
        existingCall.setFile(callsDetails.getFile());
        existingCall.setTheme(callsDetails.getTheme());
        return callRespository.save(existingCall);
    }
    @Override
    public void deleteCall(Long id) {
        Call call = callRespository.findById(id).orElseThrow(()-> new RuntimeException("call not found with id"+id));
        callRespository.delete(call);
    }

}