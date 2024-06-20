
package com.habsida.morago.serviceImpl;


import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
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

    public Deposits getDepositById(Long id) {
        return depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposits not found for id: " + id));
    }

    public Deposits addDeposit(CreateDepositsInput createDepositsInput) {

        Deposits deposits = new Deposits();
        deposits.setAccountHolder(createDepositsInput.getAccountHolder());
        deposits.setNameOfBank(createDepositsInput.getNameOfBank());
        deposits.setWon(createDepositsInput.getWon());
        deposits.setCoin(createDepositsInput.getCoin());
        deposits.setStatus(createDepositsInput.getStatus());

        User user = userRepository.findById(createDepositsInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createDepositsInput.getUserId()));

        deposits.setUser(user);

        return depositsRepository.save(deposits);
    }

    public Deposits updateDeposit(Long id, UpdateDepositsInput updateDepositsInput) {
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


        return depositsRepository.save(deposits);
    }

    public void deleteDeposit(Long id) {
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposit not found for id: " + id));
        depositsRepository.deleteById(id);

    }

    @Override
    public List<Deposits> getDepositsByStatus(PaymentStatus status) {
        List<Deposits> depositsByStatus = depositsRepository.findByStatus(status);
        if (depositsByStatus.isEmpty()) {
            throw new GraphqlException("Deposits not found for status: " + status);
        }
        return depositsByStatus;
    }

    @Override
    public List<Deposits> getDepositByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Deposits> depositsByUserId = depositsRepository.findByUserId(userId);


    return depositsByUserId;
    }

}
