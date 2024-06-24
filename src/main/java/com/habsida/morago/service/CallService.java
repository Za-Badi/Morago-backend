package com.habsida.morago.service;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CallService {
    List<Call> getAllCalls();
    Call getCallById(Long id) throws Exception;
    Call createCall(CreateCallInput callInput) throws Exception;
    Call updateCall(Long id, CallStatus status, Integer duration, Float commission);
    void deleteCall(Long id) throws Exception;
    public List<Call> getCallsByUserId(Long userId);
    public List<Call> getAllFreeCall();
    public Page<Call> getCallsByOutgoingIncomingStatus(CallStatus status, Pageable pageable);
    public Page<Call> getCallsByOutgoingIncoming(Pageable pageable);
    public Page<Call> getAllMissedCalls(Long userId, Pageable pageable);

    //    List <Call> getCallByStatus(CallStatus status);
}