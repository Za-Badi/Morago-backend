package com.habsida.morago.repository;

import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.enums.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {
//    List<Call> findByStatus(CallStatus callStatus);

    @Query("SELECT c FROM Call c WHERE c.caller.id = :userId OR c.recipient.id = :userId")
    List<Call> findCallsByUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM Call c WHERE c.status = 'INCOMING_CALL' OR c.status = 'OUTGOING_CALL'")
    List<Call> getCallsByOutgoingIncoming();

    @Query("SELECT c FROM Call c WHERE c.status = 'INCOMING_CALL' OR c.status = 'OUTGOING_CALL'")
    List<Call> getCallsByOutgoingIncomingStatus(@Param("status") CallStatus callStatus);

    @Query("SELECT c FROM Call c WHERE c.status = 'INCOMING_CALL'")
    List<Call> getCallsByMissedCall(@Param("status") CallStatus callStatus);

}