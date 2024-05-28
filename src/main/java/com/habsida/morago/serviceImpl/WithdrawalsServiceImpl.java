package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.repository.WithdrawalRepository;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalsServiceImpl implements WithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Override
    public List<Withdrawals> getAllWithdrawals() {
        return withdrawalRepository.findAll();
    }

    @Override
    public Optional<Withdrawals> getWithdrawalsById(Long id) {
        return withdrawalRepository.findById(id);
    }

    @Override
    public List<Withdrawals> getWithdrawalsByUserId(Long userId) {
        return withdrawalRepository.findByUserId(userId);
    }

    @Override
    public Withdrawals saveWithdrawals(Withdrawals withdrawals) {
        return withdrawalRepository.save(withdrawals);
    }

    @Override
    public Withdrawals updateWithdrawals(Long id, Withdrawals withdrawals) {
        Optional<Withdrawals> existingWithdrawal = withdrawalRepository.findById(id);
        if (existingWithdrawal.isPresent()) {
            Withdrawals updatedWithdrawal = existingWithdrawal.get();
            updatedWithdrawal.setAccountNumber(withdrawals.getAccountNumber());
            updatedWithdrawal.setAccountHolder(withdrawals.getAccountHolder());
            updatedWithdrawal.setNameOfBank(withdrawals.getNameOfBank());
            updatedWithdrawal.setSum(withdrawals.getSum());
            updatedWithdrawal.setStatus(withdrawals.getStatus());
            updatedWithdrawal.setUpdatedAt(withdrawals.getUpdatedAt());
            return withdrawalRepository.save(updatedWithdrawal);
        } else {
            return null;
        }
    }

    @Override
    public void deleteWithdrawals(Long id) {
        withdrawalRepository.deleteById(id);

    }
}
