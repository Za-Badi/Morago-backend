package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.repository.DepositsRepository;
import com.habsida.morago.service.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositsServiceImpl implements DepositsService {

    @Autowired
    private DepositsRepository depositsRepository;
    @Override
    public List<Deposits> getAllDeposits() {
        return depositsRepository.findAll();
    }

    @Override
    public Optional<Deposits> getDepositById(Long id) {
        return depositsRepository.findById(id);
    }

    @Override
    public List<Deposits> getDepositsByUserId(Long userId) {
        return depositsRepository.findByUserId(userId);
    }

    @Override
    public Deposits saveDeposit(Deposits deposits) {
        return depositsRepository.save(deposits);
    }

    @Override
    public Deposits updateDeposits(Long id, Deposits deposits) {
        Optional<Deposits> existingDeposit = depositsRepository.findById(id);
        if(existingDeposit.isPresent()) {
            Deposits updatedDeposit = existingDeposit.get();
            updatedDeposit.setAccountHolder(deposits.getAccountHolder());
            updatedDeposit.setNameOfBank(deposits.getNameOfBank());
            updatedDeposit.setCoin(deposits.getCoin());
            updatedDeposit.setWon(deposits.getWon());
            updatedDeposit.setStatus(deposits.getStatus());
            updatedDeposit.setCreatedAt(deposits.getCreatedAt());
            updatedDeposit.setUpdatedAt(deposits.getUpdatedAt());

            return depositsRepository.save(updatedDeposit);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDeposits(Long id) {
        depositsRepository.deleteById(id);

    }
}
