package com.habsida.morago;


import com.habsida.morago.model.dto.DebtorDTO;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.repository.DebtorRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.serviceImpl.DebtorServiceImpl;
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

class DebtorServiceImplTest {

    @Mock
    private DebtorRepository debtorRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DebtorServiceImpl debtorService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getAllDebtors() {
        Debtor debtor = new Debtor();
        when(debtorRepository.findAll()).thenReturn(Arrays.asList(debtor));
        when(modelMapper.map(any(Debtor.class), eq(DebtorDTO.class))).thenReturn(new DebtorDTO());

        List<DebtorDTO> result = debtorService.getAllDebtors();

        assertFalse(result.isEmpty());
        verify(debtorRepository).findAll();
        verify(modelMapper, times(1)).map(debtor, DebtorDTO.class);
    }

    @Test
    void getDebtorById() {
        Long id = 1L;
        Debtor debtor = new Debtor();
        when(debtorRepository.findById(id)).thenReturn(Optional.of(debtor));
        when(modelMapper.map(any(Debtor.class), eq(DebtorDTO.class))).thenReturn(new DebtorDTO());

        DebtorDTO result = debtorService.getDebtorById(id);

        assertNotNull(result);
        verify(debtorRepository).findById(id);
        verify(modelMapper).map(debtor, DebtorDTO.class);
    }

    @Test
    void addDebtorWithDetailedInput() {
        CreateDebtorInput input = new CreateDebtorInput();
        input.setUserId(1L);
        input.setAccountHolder("John Doe");
        input.setNameOfBank("Bank of Test");
        input.setIsPaid(true);

        User user = new User();
        user.setId(input.getUserId());

        Debtor mappedDebtor = new Debtor();
        mappedDebtor.setUser(user);
        mappedDebtor.setAccountHolder(input.getAccountHolder());
        mappedDebtor.setNameOfBank(input.getNameOfBank());
        mappedDebtor.setIsPaid(input.getIsPaid());

        Debtor savedDebtor = new Debtor();

        when(userRepository.findById(input.getUserId())).thenReturn(Optional.of(user));
        when(modelMapper.map(input, Debtor.class)).thenReturn(mappedDebtor);
        when(debtorRepository.save(mappedDebtor)).thenReturn(savedDebtor);
        when(modelMapper.map(savedDebtor, DebtorDTO.class)).thenReturn(new DebtorDTO());

        DebtorDTO result = debtorService.addDebtor(input);

        assertNotNull(result);
        verify(userRepository).findById(input.getUserId());
        verify(debtorRepository).save(mappedDebtor);
        verify(modelMapper).map(savedDebtor, DebtorDTO.class);
    }

    @Test
    void updateDebtorWithDetailedInput() {
        Long id = 1L;
        UpdateDebtorInput input = new UpdateDebtorInput();
        input.setAccountHolder("Updated Holder");
        input.setNameOfBank("Updated Bank");
        input.setIsPaid(true);

        Debtor existingDebtor = new Debtor();
        existingDebtor.setId(id);
        existingDebtor.setAccountHolder("Old Holder");
        existingDebtor.setNameOfBank("Old Bank");
        existingDebtor.setIsPaid(false);

        when(debtorRepository.findById(id)).thenReturn(Optional.of(existingDebtor));

        doAnswer(invocation -> {
            UpdateDebtorInput src = invocation.getArgument(0);
            Debtor dest = invocation.getArgument(1);
            dest.setAccountHolder(src.getAccountHolder());
            dest.setNameOfBank(src.getNameOfBank());
            dest.setIsPaid(src.getIsPaid());
            return null;
        }).when(modelMapper).map(any(UpdateDebtorInput.class), any(Debtor.class));

        when(debtorRepository.save(any(Debtor.class))).thenReturn(existingDebtor);

        debtorService.updateDebtor(id, input);

        assertEquals("Updated Holder", existingDebtor.getAccountHolder());
        assertEquals("Updated Bank", existingDebtor.getNameOfBank());
        assertTrue(existingDebtor.getIsPaid());
        verify(debtorRepository).save(existingDebtor);
    }

    @Test
    void deleteDebtor() {
        Long debtorId = 1L;
        Debtor debtor = new Debtor();
        debtor.setId(debtorId);

        when(debtorRepository.findById(debtorId)).thenReturn(Optional.of(debtor));
        doNothing().when(debtorRepository).deleteById(debtorId);

        debtorService.deleteDebtor(debtorId);

        verify(debtorRepository).findById(debtorId);
        verify(debtorRepository).deleteById(debtorId);
    }

    @Test
    void getDebtorByUserId() {
        Long userId = 1L;
        Debtor debtor = new Debtor();
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(debtorRepository.findByUserId(userId)).thenReturn(Arrays.asList(debtor));
        when(modelMapper.map(any(Debtor.class), eq(DebtorDTO.class))).thenReturn(new DebtorDTO());

        List<DebtorDTO> result = debtorService.getDebtorByUserId(userId);

        assertFalse(result.isEmpty());
        verify(userRepository).findById(userId);
        verify(debtorRepository).findByUserId(userId);
        verify(modelMapper).map(debtor, DebtorDTO.class);
    }
}
