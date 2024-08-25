package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {

    @Query("SELECT c FROM Call c WHERE c.caller.id = :userId OR c.recipient.id = :userId OR c.recipientConsultant.id = :userId")
    Page<Call> findCallsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT c FROM Call c WHERE c.status = 'INCOMING_CALL' OR c.status = 'OUTGOING_CALL'")
    Page<Call> getCallsByOutgoingIncoming(Pageable pageable);

    @Query("SELECT c FROM Call c WHERE c.status = :status")
    Page<Call> getCallsByOutgoingIncomingStatus(@Param("status") CallStatus status, Pageable pageable);

    @Query("SELECT c FROM Call c WHERE c.status = 'MISSED_CALL' AND c.caller.id = :userId OR c.recipient.id = :userId  OR c.recipientConsultant.id = :userId")
    Page<Call> getAllMissedCalls(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT c FROM Call c " +
            "WHERE c.caller.id = :userId OR c.recipient.id = :userId  OR c.recipientConsultant.id = :userId")
    Page<Call> findAllCallsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT c FROM Call c WHERE c.commission = 0 OR c.commission is NULL")
    Page<Call> getFreeCallIsMade(Pageable pageable);

//    List<Call> findByStatus(CallStatus callStatus);
}


