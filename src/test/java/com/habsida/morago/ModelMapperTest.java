package com.habsida.morago;

import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelMapperTest {

    @Test
    public void testDepositsToDepositsDTO() {
        ModelMapper modelMapper = new ModelMapper();

        Deposits deposits = new Deposits();
        deposits.setId(1L);
        deposits.setAccountHolder("John Doe");
        deposits.setNameOfBank("Bank XYZ");
        deposits.setCoin(100.0);
        deposits.setWon(200.0);
        deposits.setStatus(PaymentStatus.COMPLETED);
        deposits.setCreatedAt(LocalDateTime.now());
        deposits.setUpdatedAt(LocalDateTime.now());

        DepositsDTO depositsDTO = modelMapper.map(deposits, DepositsDTO.class);

        assertEquals(deposits.getId(), depositsDTO.getId());
        assertEquals(deposits.getAccountHolder(), depositsDTO.getAccountHolder());
        assertEquals(deposits.getNameOfBank(), depositsDTO.getNameOfBank());
        assertEquals(deposits.getCoin(), depositsDTO.getCoin());
        assertEquals(deposits.getWon(), depositsDTO.getWon());
        assertEquals(deposits.getStatus(), depositsDTO.getStatus());
        assertEquals(deposits.getCreatedAt(), depositsDTO.getCreatedAt());
        assertEquals(deposits.getUpdatedAt(), depositsDTO.getUpdatedAt());
    }
}
