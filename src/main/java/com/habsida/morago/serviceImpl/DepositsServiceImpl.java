package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.enums.Status;
import com.habsida.morago.model.input.DepositsInput;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.DepositsRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositsServiceImpl implements DepositsService {
    private final DepositsRepository depositsRepository;
    private final UserRepository userRepository;

    @Autowired
    public DepositsServiceImpl(DepositsRepository depositsRepository, UserRepository userRepository) {
        this.depositsRepository = depositsRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Deposits> getAllDeposits() {
        return depositsRepository.findAll();
    }

    public Deposits getDepositById(Long id) throws Exception {
        return depositsRepository.findById(id)
                .orElseThrow(() -> new Exception("Deposits not found for id: " + id));
    }

    public Deposits addDeposit(DepositsInput depositsInput) throws Exception {
        Deposits deposits = new Deposits();
        deposits.setAccountHolder(deposits.getAccountHolder());
        deposits.setNameOfBank(deposits.getNameOfBank());
        deposits.setWon(deposits.getWon());
        deposits.setCoin(deposits.getCoin());
        deposits.setStatus(depositsInput.getStatus());
        User user = userRepository.findById(depositsInput.getUserId())
                .orElseThrow(() -> new Exception("User not found with id " + depositsInput.getUserId()));
        deposits.setUser(user);

        return depositsRepository.save(deposits);
    }

    public Deposits updateDeposit(Long id, DepositsInput depositsInput) throws Exception {
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new Exception("Deposits not found for id: " + id));
        if (depositsInput.getAccountHolder() != null && !depositsInput.getAccountHolder().isEmpty()) {
            deposits.setAccountHolder(depositsInput.getAccountHolder());
        }
        if (depositsInput.getNameOfBank() != null && !depositsInput.getNameOfBank().isEmpty()) {
            deposits.setNameOfBank(depositsInput.getNameOfBank());
        }

        if (depositsInput.getCoin() != null) {
            deposits.setCoin(depositsInput.getCoin());
        }
        if (depositsInput.getWon() != null) {
            deposits.setWon(depositsInput.getWon());
        }
        if (depositsInput.getStatus() != null) {
            deposits.setStatus(depositsInput.getStatus());
        }

        return depositsRepository.save(deposits);
    }

    @Override
    public void deleteDeposit(Long id) throws Exception {
        Deposits deposit = depositsRepository.findById(id)
                .orElseThrow(() -> new Exception("Deposit not found with id " + id));
        depositsRepository.delete(deposit);
    }

    @Override
    public List<Deposits> getDepositsByStatus(Status status) {
        return null;
    }

    @Override
    public List<Deposits> getDepositByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        return depositsRepository.findByUser(user);
    }
}