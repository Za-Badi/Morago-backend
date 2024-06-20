package com.habsida.morago.serviceImpl;


import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.WithdrawalRepository;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    private WithdrawalRepository withdrawalRepository;
    ;
    private UserRepository userRepository;

    public WithdrawalServiceImpl(WithdrawalRepository withdrawalRepository, UserRepository userRepository) {
        this.withdrawalRepository = withdrawalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Withdrawals> getAllWithdrawals() {
        return withdrawalRepository.findAll();
    }

    @Override
    public Withdrawals getWithdrawalById(Long id) {
        return withdrawalRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Withdrawal not found for id: " + id));
    }


    @Override
    public Withdrawals addWithdrawal(CreateWithdrawalInput createWithdrawalInput) {
        User user = userRepository.findById(createWithdrawalInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createWithdrawalInput.getUserId()));

        Withdrawals withdrawals = new Withdrawals();
        withdrawals.setAccountNumber(createWithdrawalInput.getAccountNumber());
        withdrawals.setAccountHolder(createWithdrawalInput.getAccountHolder());
        withdrawals.setSum(createWithdrawalInput.getSum());
        withdrawals.setStatus(createWithdrawalInput.getStatus());
        withdrawals.setUser(user);

        return withdrawalRepository.save(withdrawals);
    }

    @Override
    public Withdrawals updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput) {
        Withdrawals withdrawals = withdrawalRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Withdrawal not found for id: " + id));

        if (updateWithdrawalInput.getAccountNumber() != null && !updateWithdrawalInput.getAccountNumber().isEmpty()) {
            withdrawals.setAccountNumber(updateWithdrawalInput.getAccountNumber());
        }
        if (updateWithdrawalInput.getAccountNumber() != null && !updateWithdrawalInput.getAccountHolder().isEmpty()) {
            withdrawals.setAccountHolder(updateWithdrawalInput.getAccountHolder());
        }
        if (updateWithdrawalInput.getSum() != null) {
            withdrawals.setSum(updateWithdrawalInput.getSum());
        }
        if (updateWithdrawalInput.getStatus() != null) {
            withdrawals.setStatus(updateWithdrawalInput.getStatus());
        }

        return withdrawalRepository.save(withdrawals);

    }

    @Override
    public void deleteWithdrawal(Long id)  {
        Withdrawals withdrawals = withdrawalRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Withdrawal not found for id: " + id));
        withdrawalRepository.deleteById(id);

    }


    @Override
    public List<Withdrawals> getWithdrawalsByStatus(PaymentStatus status) {
        List<Withdrawals> withdrawalsByStatus = withdrawalRepository.findByStatus(status);
        if (withdrawalsByStatus.isEmpty()){
            throw new GraphqlException("Deposits not found for status: " + status);
        }
        return withdrawalsByStatus;
    }

    @Override
    public List<Withdrawals> getWithdrawalsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Withdrawals> withdrawalsByUserId = withdrawalRepository.findByUserId(userId);

        return withdrawalsByUserId;
    }
}