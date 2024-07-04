package com.habsida.morago;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.repository.DepositsRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.DepositsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DepositsServiceImplTest {

    @Mock
    private DepositsRepository depositsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepositsServiceImpl depositsService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAllDeposits() {
        Deposits deposit = new Deposits();
        when(depositsRepository.findAllWithUsers()).thenReturn(Arrays.asList(deposit));
        when(modelMapper.map(any(Deposits.class), eq(DepositsDTO.class))).thenReturn(new DepositsDTO());

        List<DepositsDTO> result = depositsService.getAllDeposits();

        assertFalse(result.isEmpty());
        verify(depositsRepository).findAllWithUsers();
        verify(modelMapper, times(1)).map(deposit, DepositsDTO.class);
    }

    @Test
    void getDepositById() {
        Long id = 1L;
        Deposits deposit = new Deposits();
        when(depositsRepository.findByIdWithUser(id)).thenReturn(Optional.of(deposit));
        when(modelMapper.map(any(Deposits.class), eq(DepositsDTO.class))).thenReturn(new DepositsDTO());

        DepositsDTO result = depositsService.getDepositById(id);

        assertNotNull(result);
        verify(depositsRepository).findByIdWithUser(id);
        verify(modelMapper).map(deposit, DepositsDTO.class);
    }

    @Test
    void addDepositWithDetailedInput() {
        CreateDepositsInput input = new CreateDepositsInput();
        input.setUserId(1L);
        input.setAccountHolder("John Doe");
        input.setNameOfBank("Bank of Test");
        input.setCoin(150.50);
        input.setWon(120000.0);

        User user = new User();
        user.setId(input.getUserId());

        Deposits mappedDeposit = new Deposits();
        mappedDeposit.setUser(user);
        mappedDeposit.setAccountHolder(input.getAccountHolder());
        mappedDeposit.setNameOfBank(input.getNameOfBank());
        mappedDeposit.setCoin(input.getCoin());
        mappedDeposit.setWon(input.getWon());

        Deposits savedDeposit = new Deposits();

        when(userRepository.findById(input.getUserId())).thenReturn(Optional.of(user));
        when(modelMapper.map(input, Deposits.class)).thenReturn(mappedDeposit);
        when(depositsRepository.save(mappedDeposit)).thenReturn(savedDeposit);
        when(modelMapper.map(savedDeposit, DepositsDTO.class)).thenReturn(new DepositsDTO());

        DepositsDTO result = depositsService.addDeposit(input);

        assertNotNull(result);
        verify(userRepository).findById(input.getUserId());
        verify(depositsRepository).save(mappedDeposit);
        verify(modelMapper).map(savedDeposit, DepositsDTO.class);
    }


    @Test
    void updateDepositWithDetailedInput() {
        Long id = 1L;
        UpdateDepositsInput input = new UpdateDepositsInput();
        input.setAccountHolder("Updated Holder");
        input.setNameOfBank("Updated Bank");
        input.setCoin(200.0);
        input.setWon(240000.0);
        input.setStatus(PaymentStatus.COMPLETED);

        Deposits existingDeposit = new Deposits();
        existingDeposit.setId(id);
        existingDeposit.setAccountHolder("Old Holder");
        existingDeposit.setNameOfBank("Old Bank");
        existingDeposit.setCoin(100.0);
        existingDeposit.setWon(120000.0);
        existingDeposit.setStatus(PaymentStatus.PENDING);

        when(depositsRepository.findById(id)).thenReturn(Optional.of(existingDeposit));

        doAnswer(invocation -> {
            UpdateDepositsInput src = invocation.getArgument(0);
            Deposits dest = invocation.getArgument(1);
            dest.setAccountHolder(src.getAccountHolder());
            dest.setNameOfBank(src.getNameOfBank());
            dest.setCoin(src.getCoin());
            dest.setWon(src.getWon());
            dest.setStatus(src.getStatus());
            return null;
        }).when(modelMapper).map(any(UpdateDepositsInput.class), any(Deposits.class));


        when(depositsRepository.save(any(Deposits.class))).thenReturn(existingDeposit);

        depositsService.updateDeposit(id, input);

    }



    @Test
    public void deleteDeposit() {
        Long depositId = 1L;
        Deposits deposit = new Deposits();
        deposit.setId(depositId);

        when(depositsRepository.findById(depositId)).thenReturn(Optional.of(deposit));
        doNothing().when(depositsRepository).delete(deposit);

        depositsService.deleteDeposit(depositId);

        verify(depositsRepository).findById(depositId);
        verify(depositsRepository).delete(deposit);
    }
}
