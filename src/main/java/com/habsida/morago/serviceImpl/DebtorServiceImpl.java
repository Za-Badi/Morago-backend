package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.DebtorDTO;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.repository.DebtorRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DebtorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebtorServiceImpl implements DebtorService {
    private final DebtorRepository debtorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DebtorServiceImpl(DebtorRepository debtorRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.debtorRepository = debtorRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DebtorDTO> getAllDebtors() {
        List<Debtor> debtors = debtorRepository.findAll();
        return debtors.stream()
                .map(debtor -> modelMapper.map(debtor, DebtorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DebtorDTO getDebtorById(Long id) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));
        return modelMapper.map(debtor, DebtorDTO.class);
    }

    @Override
    public DebtorDTO addDebtor(CreateDebtorInput createDebtorInput) {
        Debtor debtor = new Debtor();
        debtor.setAccountHolder(createDebtorInput.getAccountHolder());
        debtor.setNameOfBank(createDebtorInput.getNameOfBank());
        debtor.setIsPaid(createDebtorInput.getIsPaid());
        User user = userRepository.findById(createDebtorInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createDebtorInput.getUserId()));
        debtor.setUser(user);
        Debtor savedDebtor = debtorRepository.save(debtor);
        return modelMapper.map(savedDebtor, DebtorDTO.class);
    }

    @Override
    public DebtorDTO updateDebtor(Long id, UpdateDebtorInput updateDebtorInput) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));

        if (updateDebtorInput.getAccountHolder() != null && !updateDebtorInput.getAccountHolder().isEmpty()) {
            debtor.setAccountHolder(updateDebtorInput.getAccountHolder());
        }
        if (updateDebtorInput.getNameOfBank() != null && !updateDebtorInput.getNameOfBank().isEmpty()) {
            debtor.setNameOfBank(updateDebtorInput.getNameOfBank());
        }
        if (updateDebtorInput.getIsPaid() != null) {
            debtor.setIsPaid(updateDebtorInput.getIsPaid());
        }

        Debtor updatedDebtor = debtorRepository.save(debtor);
        return modelMapper.map(updatedDebtor, DebtorDTO.class);
    }

    @Override
    public void deleteDebtor(Long id) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));
        debtorRepository.deleteById(id);
    }

    @Override
    public List<DebtorDTO> getDebtorByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Debtor> debtors = debtorRepository.findByUserId(userId);
        return debtors.stream()
                .map(debtor -> modelMapper.map(debtor, DebtorDTO.class))
                .collect(Collectors.toList());
    }
}
