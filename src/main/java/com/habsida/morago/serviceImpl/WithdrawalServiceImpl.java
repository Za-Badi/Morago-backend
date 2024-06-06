package com.habsida.morago.serviceImpl;


import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.inputs.WithdrawalInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.WithdrawalRepository;
import com.habsida.morago.service.WithdrawalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    private WithdrawalRepository withdrawalRepository;;
    private UserRepository userRepository;

    public WithdrawalServiceImpl(WithdrawalRepository withdrawalRepository, UserRepository userRepository){
        this.withdrawalRepository = withdrawalRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Withdrawals> getAllWithdrawals() {
        return withdrawalRepository.findAll();
    }

    @Override
    public Withdrawals getWithdrawalById(Long id) throws Exception {
        return withdrawalRepository.findById(id)
                .orElseThrow(() -> new Exception("Withdrawal not found for id: " + id));
    }


    @Override
    public Withdrawals addWithdrawal(WithdrawalInput withdrawalDto) {
        User user = userRepository.findById(withdrawalDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Withdrawals withdrawals = new Withdrawals();
        withdrawals.setAccountNumber(withdrawalDto.getAccountNumber());
        withdrawals.setAccountHolder(withdrawalDto.getAccountHolder());
        withdrawals.setSum(withdrawalDto.getSum());
        withdrawals.setStatus(withdrawalDto.getStatus());
        withdrawals.setUser(user);

        return withdrawalRepository.save(withdrawals);
    }

    @Override
    public Withdrawals updateWithdrawal(Long id, WithdrawalInput withdrawalDto) throws Exception {
        Withdrawals withdrawals = withdrawalRepository.findById(withdrawalDto.getUserId())
                .orElseThrow(() -> new Exception("Withdrawals not found for id: " + id));

        if(withdrawalDto.getAccountNumber() != null) {
            withdrawals.setAccountNumber(withdrawalDto.getAccountNumber());
        }
        if(withdrawalDto.getAccountNumber() != null) {
            withdrawals.setAccountNumber(withdrawalDto.getAccountNumber());
        }
        if(withdrawalDto.getSum() != null) {
            withdrawals.setSum(withdrawalDto.getSum());
        }
        if(withdrawalDto.getStatus() != null) {
            withdrawals.setStatus(withdrawalDto.getStatus());
        }
        if(withdrawalDto.getUserId() != null){
            User user = userRepository.findById(withdrawalDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            withdrawals.setUser(user);
        }

        return withdrawalRepository.save(withdrawals);

    }

    @Override
    public void deleteWithdrawal(Long id) throws Exception {
        withdrawalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Withdrawal not found for id: " + id));
        withdrawalRepository.deleteById(id);

    }
}