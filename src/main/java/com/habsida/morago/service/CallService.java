package com.habsida.morago.service;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CreateCallInput;

import java.util.List;

public interface CallService {
    List<Call> getAllCalls();
    Call getCallById(Long id) throws Exception;
    Call createCall(CreateCallInput callInput) throws Exception;
    Call updateCall(Long id, CallStatus status, Integer duration, Float commission) throws Exception;
    void deleteCall(Long id) throws Exception;

    public List<Call> getCallsByUserId(Long userId);
    List<Call> getAllMissedCalls( CallStatus callStatus) throws Exception;

//    List <Call> getCallByStatus(CallStatus status);

    List<Call> getCallsByOutgoingIncoming();
    List<Call> getCallsByOutgoingIncomingStatus(CallStatus callStatus);


}