package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.RatingDTO;
import com.habsida.morago.model.dto.WithdrawalsDTO;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.entity.Withdrawals;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateWithdrawalInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateWithdrawalInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.repository.WithdrawalRepository;
import com.habsida.morago.service.WithdrawalService;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WithdrawalServiceImpl implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public PageOutput<WithdrawalsDTO> getAllWithdrawals(PagingInput pagingInput) {
        Page<Withdrawals> withdrawalsPage = withdrawalRepository.findAll(PageUtil.buildPageable(pagingInput));
        return new PageOutput<>(
                withdrawalsPage.getNumber(),
                withdrawalsPage.getTotalPages(),
                withdrawalsPage.getTotalElements(),
                withdrawalsPage.getContent().stream()
                        .map(withdrawals -> modelMapperUtil.map(withdrawals, WithdrawalsDTO.class))
                        .collect(Collectors.toList())
        );
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
    public PageOutput<WithdrawalsDTO> getWithdrawalsByStatus(PaymentStatus status, PagingInput pagingInput) {
        Page<Withdrawals> withdrawalsByStatus = withdrawalRepository.findByStatus(status, PageUtil.buildPageable(pagingInput));
        if (withdrawalsByStatus.getContent().isEmpty()) {
            throw new GraphqlException("Withdrawals not found for status: " + status);
        }
        return new PageOutput<>(
                withdrawalsByStatus.getNumber(),
                withdrawalsByStatus.getTotalPages(),
                withdrawalsByStatus.getTotalElements(),
                withdrawalsByStatus.getContent().stream()
                        .map(withdrawals -> modelMapperUtil.map(withdrawals, WithdrawalsDTO.class))
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public PageOutput<WithdrawalsDTO> getWithdrawalsByUserId(Long userId, PagingInput pagingInput) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        Page<Withdrawals> withdrawalsByUserId = withdrawalRepository.findByUserId(userId, PageUtil.buildPageable(pagingInput));
        return new PageOutput<>(
                withdrawalsByUserId.getNumber(),
                withdrawalsByUserId.getTotalPages(),
                withdrawalsByUserId.getTotalElements(),
                withdrawalsByUserId.getContent().stream()
                        .map(withdrawals -> modelMapperUtil.map(withdrawals, WithdrawalsDTO.class))
                        .collect(Collectors.toList())
        );
    }
}
