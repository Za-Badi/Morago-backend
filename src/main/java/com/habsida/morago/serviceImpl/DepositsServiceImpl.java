package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.repository.DepositsRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DepositsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepositsServiceImpl implements DepositsService {
    private final DepositsRepository depositsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DepositsServiceImpl(DepositsRepository depositsRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.depositsRepository = depositsRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepositsDTO> getAllDeposits() {
        return depositsRepository.findAll()
                .stream()
                .map(deposits -> modelMapper.map(deposits, DepositsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepositsDTO getDepositById(Long id) {
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposits not found for id: " + id));
        return modelMapper.map(deposits, DepositsDTO.class);
    }

    @Override
    public DepositsDTO addDeposit(CreateDepositsInput createDepositsInput) {
        Deposits deposits = new Deposits();
        deposits.setAccountHolder(createDepositsInput.getAccountHolder());
        deposits.setNameOfBank(createDepositsInput.getNameOfBank());
        deposits.setWon(createDepositsInput.getWon());
        deposits.setCoin(createDepositsInput.getCoin());
        deposits.setStatus(createDepositsInput.getStatus());

        User user = userRepository.findById(createDepositsInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createDepositsInput.getUserId()));

        deposits.setUser(user);
        Deposits savedDeposits = depositsRepository.save(deposits);

        return modelMapper.map(savedDeposits, DepositsDTO.class);
    }

    @Override
    public DepositsDTO updateDeposit(Long id, UpdateDepositsInput updateDepositsInput) {
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposits not found for id: " + id));
        if (updateDepositsInput.getAccountHolder() != null && !updateDepositsInput.getAccountHolder().isEmpty()) {
            deposits.setAccountHolder(updateDepositsInput.getAccountHolder());
        }
        if (updateDepositsInput.getNameOfBank() != null && !updateDepositsInput.getNameOfBank().isEmpty()) {
            deposits.setNameOfBank(updateDepositsInput.getNameOfBank());
        }
        if (updateDepositsInput.getCoin() != null) {
            deposits.setCoin(updateDepositsInput.getCoin());
        }
        if (updateDepositsInput.getWon() != null) {
            deposits.setWon(updateDepositsInput.getWon());
        }
        if (updateDepositsInput.getStatus() != null) {
            deposits.setStatus(updateDepositsInput.getStatus());
        }

        Deposits updatedDeposits = depositsRepository.save(deposits);
        return modelMapper.map(updatedDeposits, DepositsDTO.class);
    }

    @Override
    public void deleteDeposit(Long id) {
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposit not found for id: " + id));
        depositsRepository.deleteById(id);
    }

    @Override
    public List<DepositsDTO> getDepositsByStatus(PaymentStatus status) {
        List<Deposits> depositsByStatus = depositsRepository.findByStatus(status);
        if (depositsByStatus.isEmpty()) {
            throw new GraphqlException("Deposits not found for status: " + status);
        }
        return depositsByStatus.stream()
                .map(deposits -> modelMapper.map(deposits, DepositsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DepositsDTO> getDepositByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Deposits> depositsByUserId = depositsRepository.findByUserId(userId);
        return depositsByUserId.stream()
                .map(deposits -> modelMapper.map(deposits, DepositsDTO.class))
                .collect(Collectors.toList());
    }
}
