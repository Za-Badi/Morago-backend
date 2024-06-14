package com.habsida.morago.service;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CallInput;

import java.util.List;

public interface CallService {
    List<Call> getAllCalls();
    Call getCallById(Long id) throws Exception;
    Call createCall(CallInput callInput);
    Call updateCall(Long id, CallStatus status, Integer duration, Float commission) throws Exception;
    void deleteCall(Long id) throws Exception;

    List<Call> getCallByUserId(Long id);
    List<Call> getAllMissedCall(CallStatus callStatus);

    List <Call> getCallByStatus(CallStatus status);

}