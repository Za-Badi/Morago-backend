package com.habsida.morago.service;

import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CallService {
    public List<CallDTO> getAllCalls();
    public CallDTO getCallById(Long id) throws Exception;
    public CallDTO createCall(CreateCallInput callInput) throws Exception;
    public CallDTO updateCall(Long id, CallStatus status, Integer duration, Float commission);
    public void deleteCall(Long id) throws Exception;
    public List<CallDTO> getCallsByUserId(Long userId);
    public List<CallDTO> getAllFreeCall();
    public Page<CallDTO> getCallsByOutgoingIncomingStatus(CallStatus status, Pageable pageable);
    public Page<CallDTO> getCallsByOutgoingIncoming(Pageable pageable);
    public Page<CallDTO> getAllMissedCalls(Long userId, Pageable pageable);
}
