package com.habsida.morago;


import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.WithdrawalRepository;
import com.habsida.morago.serviceImpl.WithdrawalServiceImpl;
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

class WithdrawalServiceImplTest {

    @Mock
    private WithdrawalRepository withdrawalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAllWithdrawals() {
        Withdrawals withdrawal = new Withdrawals();
        when(withdrawalRepository.findAll()).thenReturn(Arrays.asList(withdrawal));
        when(modelMapper.map(any(Withdrawals.class), eq(WithdrawalsDTO.class))).thenReturn(new WithdrawalsDTO());

        List<WithdrawalsDTO> result = withdrawalService.getAllWithdrawals();

        assertFalse(result.isEmpty());
        verify(withdrawalRepository).findAll();
        verify(modelMapper, times(1)).map(withdrawal, WithdrawalsDTO.class);
    }

    @Test
    void getWithdrawalById() {
        Long id = 1L;
        Withdrawals withdrawal = new Withdrawals();
        when(withdrawalRepository.findById(id)).thenReturn(Optional.of(withdrawal));
        when(modelMapper.map(any(Withdrawals.class), eq(WithdrawalsDTO.class))).thenReturn(new WithdrawalsDTO());

        WithdrawalsDTO result = withdrawalService.getWithdrawalById(id);

        assertNotNull(result);
        verify(withdrawalRepository).findById(id);
        verify(modelMapper).map(withdrawal, WithdrawalsDTO.class);
    }

    @Test
    void addWithdrawalWithDetailedInput() {
        CreateWithdrawalInput input = new CreateWithdrawalInput();
        input.setUserId(1L);
        input.setAccountNumber("123456789");
        input.setAccountHolder("John Doe");
        input.setSum(500.0F);
        input.setStatus(PaymentStatus.PENDING);

        User user = new User();
        user.setId(input.getUserId());

        Withdrawals mappedWithdrawal = new Withdrawals();
        mappedWithdrawal.setUser(user);
        mappedWithdrawal.setAccountNumber(input.getAccountNumber());
        mappedWithdrawal.setAccountHolder(input.getAccountHolder());
        mappedWithdrawal.setSum(input.getSum());
        mappedWithdrawal.setStatus(input.getStatus());

        Withdrawals savedWithdrawal = new Withdrawals();

        when(userRepository.findById(input.getUserId())).thenReturn(Optional.of(user));
        when(withdrawalRepository.save(any(Withdrawals.class))).thenReturn(savedWithdrawal);
        when(modelMapper.map(savedWithdrawal, WithdrawalsDTO.class)).thenReturn(new WithdrawalsDTO());

        WithdrawalsDTO result = withdrawalService.addWithdrawal(input);

        assertNotNull(result);
        verify(userRepository).findById(input.getUserId());
        verify(withdrawalRepository).save(any(Withdrawals.class));
        verify(modelMapper).map(savedWithdrawal, WithdrawalsDTO.class);
    }

    @Test
    void updateWithdrawalWithDetailedInput() {
        Long id = 1L;
        UpdateWithdrawalInput input = new UpdateWithdrawalInput();
        input.setAccountNumber("987654321");
        input.setAccountHolder("Jane Doe");
        input.setSum(1000.0F);
        input.setStatus(PaymentStatus.COMPLETED);

        Withdrawals existingWithdrawal = new Withdrawals();
        existingWithdrawal.setId(id);
        existingWithdrawal.setAccountNumber("123456789");
        existingWithdrawal.setAccountHolder("John Doe");
        existingWithdrawal.setSum(500.0F);
        existingWithdrawal.setStatus(PaymentStatus.PENDING);

        when(withdrawalRepository.findById(id)).thenReturn(Optional.of(existingWithdrawal));

        doAnswer(invocation -> {
            UpdateWithdrawalInput src = invocation.getArgument(0);
            Withdrawals dest = invocation.getArgument(1);
            dest.setAccountNumber(src.getAccountNumber());
            dest.setAccountHolder(src.getAccountHolder());
            dest.setSum(src.getSum());
            dest.setStatus(src.getStatus());
            return null;
        }).when(modelMapper).map(any(UpdateWithdrawalInput.class), any(Withdrawals.class));

        when(withdrawalRepository.save(any(Withdrawals.class))).thenReturn(existingWithdrawal);

        withdrawalService.updateWithdrawal(id, input);
    }

    @Test
    void deleteWithdrawal() {
        Long withdrawalId = 1L;
        Withdrawals withdrawal = new Withdrawals();
        withdrawal.setId(withdrawalId);

        when(withdrawalRepository.findById(withdrawalId)).thenReturn(Optional.of(withdrawal));
        doNothing().when(withdrawalRepository).deleteById(withdrawalId);

        withdrawalService.deleteWithdrawal(withdrawalId);

        verify(withdrawalRepository).findById(withdrawalId);
        verify(withdrawalRepository).deleteById(withdrawalId);
    }

    @Test
    void getWithdrawalsByStatus() {
        PaymentStatus status = PaymentStatus.PENDING;
        Withdrawals withdrawal = new Withdrawals();
        when(withdrawalRepository.findByStatus(status)).thenReturn(Arrays.asList(withdrawal));
        when(modelMapper.map(any(Withdrawals.class), eq(WithdrawalsDTO.class))).thenReturn(new WithdrawalsDTO());

        List<WithdrawalsDTO> result = withdrawalService.getWithdrawalsByStatus(status);

        assertFalse(result.isEmpty());
        verify(withdrawalRepository).findByStatus(status);
        verify(modelMapper).map(withdrawal, WithdrawalsDTO.class);
    }

    @Test
    void getWithdrawalsByUserId() {
        Long userId = 1L;
        Withdrawals withdrawal = new Withdrawals();
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(withdrawalRepository.findByUserId(userId)).thenReturn(Arrays.asList(withdrawal));
        when(modelMapper.map(any(Withdrawals.class), eq(WithdrawalsDTO.class))).thenReturn(new WithdrawalsDTO());

        List<WithdrawalsDTO> result = withdrawalService.getWithdrawalsByUserId(userId);

        assertFalse(result.isEmpty());
        verify(userRepository).findById(userId);
        verify(withdrawalRepository).findByUserId(userId);
        verify(modelMapper).map(withdrawal, WithdrawalsDTO.class);
    }
}
