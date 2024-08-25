package com.habsida.morago.service;

import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.enums.CallStatus;
import com.habsida.morago.model.inputs.CreateCallInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.results.PageOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CallService {
    public PageOutput<CallDTO> getAllCalls(PagingInput pagingInput);
    public CallDTO getCallById(Long id) throws Exception;
    public CallDTO createCall(String channelName, Long translatorId, Long userId, Long themeId) throws Exception; // New method
    public CallDTO updateCall(Long id, CallStatus status, Integer duration, Float commission);
    public void endCall(Long callId, CallStatus status, Integer duration); // New method
    public void rateCall(Long userId, Long callId, double grade); // New method
    public void deleteCall(Long id) throws Exception;
//    public List<CallDTO> getCallsByUserId(Long userId);
    public PageOutput<CallDTO> getAllFreeCall(PagingInput pagingInput);
//    public Page<CallDTO> getCallsByOutgoingIncomingStatus(CallStatus status, Pageable pageable);
//    public Page<CallDTO> getCallsByOutgoingIncoming(Pageable pageable);
//    public Page<CallDTO> getAllMissedCalls(Long userId, Pageable pageable);
    public PageOutput<CallDTO> getAllMissedCalls(Long userId, PagingInput pagingInput);
    public PageOutput<CallDTO> getCallsByOutgoingIncomingStatus(CallStatus status, PagingInput pagingInput);

    public PageOutput<CallDTO> getCallsByOutgoingIncoming(PagingInput pagingInput);

    public CallDTO createConsultantCall(String channelName, Long translatorId, Long userId, Long consultantId, Long themeId) throws Exception;
    public void endConsultantCall(Long callId, CallStatus status, Integer duration);
    public void rateConsultantCall(Long userId, Long callId, double translatorGrade, double consultantGrade);
    public PageOutput<CallDTO> getCallsByUserId(Long userId, PagingInput pagingInput);
}
