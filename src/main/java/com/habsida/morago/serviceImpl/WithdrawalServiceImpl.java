package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.WithdrawalRepository;
import com.habsida.morago.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<WithdrawalsDTO> getAllWithdrawals() {
        return withdrawalRepository.findAll().stream()
                .map(withdrawals -> modelMapper.map(withdrawals, WithdrawalsDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WithdrawalsDTO getWithdrawalById(Long id) {
        Withdrawals withdrawals = withdrawalRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Withdrawal not found for id: " + id));
        return modelMapper.map(withdrawals, WithdrawalsDTO.class);
    }

    @Transactional
    public WithdrawalsDTO addWithdrawal(CreateWithdrawalInput createWithdrawalInput) {
        if (createWithdrawalInput.getAccountHolder() == null || createWithdrawalInput.getAccountHolder().isBlank()
                || createWithdrawalInput.getAccountNumber() == null || createWithdrawalInput.getAccountNumber().isBlank()) {
            throw new GraphqlException("Account holder and number are required");
        }
        User user = userRepository.findById(createWithdrawalInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createWithdrawalInput.getUserId()));

        Withdrawals withdrawals = new Withdrawals();
        withdrawals.setAccountNumber(createWithdrawalInput.getAccountNumber());
        withdrawals.setAccountHolder(createWithdrawalInput.getAccountHolder());
        withdrawals.setSum(createWithdrawalInput.getSum());
        withdrawals.setStatus(createWithdrawalInput.getStatus());
        withdrawals.setUser(user);

        Withdrawals savedWithdrawals = withdrawalRepository.save(withdrawals);
        return modelMapper.map(savedWithdrawals, WithdrawalsDTO.class);
    }

    @Transactional
    public WithdrawalsDTO updateWithdrawal(Long id, UpdateWithdrawalInput updateWithdrawalInput) {
        Withdrawals withdrawals = withdrawalRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Withdrawal not found for id: " + id));

        if (updateWithdrawalInput.getAccountNumber() != null && !updateWithdrawalInput.getAccountNumber().isBlank()) {
            withdrawals.setAccountNumber(updateWithdrawalInput.getAccountNumber());
        }
        if (updateWithdrawalInput.getAccountHolder() != null && !updateWithdrawalInput.getAccountHolder().isBlank()) {
            withdrawals.setAccountHolder(updateWithdrawalInput.getAccountHolder());
        }
        if (updateWithdrawalInput.getSum() != null) {
            withdrawals.setSum(updateWithdrawalInput.getSum());
        }
        if (updateWithdrawalInput.getStatus() != null) {
            withdrawals.setStatus(updateWithdrawalInput.getStatus());
        }

        Withdrawals updatedWithdrawals = withdrawalRepository.save(withdrawals);
        return modelMapper.map(updatedWithdrawals, WithdrawalsDTO.class);
    }

    @Transactional
    public void deleteWithdrawal(Long id) {
        Withdrawals withdrawals = withdrawalRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Withdrawal not found for id: " + id));
        withdrawalRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<WithdrawalsDTO> getWithdrawalsByStatus(PaymentStatus status) {
        List<Withdrawals> withdrawalsByStatus = withdrawalRepository.findByStatus(status);
        if (withdrawalsByStatus.isEmpty()) {
            throw new GraphqlException("Withdrawals not found for status: " + status);
        }
        return withdrawalsByStatus.stream()
                .map(withdrawals -> modelMapper.map(withdrawals, WithdrawalsDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<WithdrawalsDTO> getWithdrawalsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Withdrawals> withdrawalsByUserId = withdrawalRepository.findByUserId(userId);
        return withdrawalsByUserId.stream()
                .map(withdrawals -> modelMapper.map(withdrawals, WithdrawalsDTO.class))
                .collect(Collectors.toList());
    }
}
