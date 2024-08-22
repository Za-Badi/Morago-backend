package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.DepositsDTO;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.enums.PaymentStatus;
import com.habsida.morago.model.inputs.CreateDepositsInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateDepositsInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.DepositsRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DepositsService;
import com.habsida.morago.util.ModelMapperUtil;
import com.habsida.morago.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepositsServiceImpl implements DepositsService {
    private final DepositsRepository depositsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public DepositsDTO getDepositById(Long id) {
        Deposits deposit = depositsRepository.findByIdWithUser(id)
                .orElseThrow(() -> new GraphqlException("Deposits not found for id: " + id));
        return modelMapper.map(deposit, DepositsDTO.class);
    }

    @Transactional
    public DepositsDTO addDeposit(CreateDepositsInput createDepositsInput) {
        if (createDepositsInput.getAccountHolder() == null || createDepositsInput.getAccountHolder().isBlank()
                || createDepositsInput.getNameOfBank() == null || createDepositsInput.getNameOfBank().isBlank()) {
            throw new GraphqlException("Account holder and name of bank are required");
        }
        User user = userRepository.findById(createDepositsInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createDepositsInput.getUserId()));

        Deposits deposits = modelMapper.map(createDepositsInput, Deposits.class);
        deposits.setUser(user);
        Deposits savedDeposits = depositsRepository.save(deposits);
        return modelMapper.map(savedDeposits, DepositsDTO.class);
    }

    @Transactional
    public DepositsDTO updateDeposit(Long id, UpdateDepositsInput updateDepositsInput) {
        if (updateDepositsInput.getAccountHolder() == null || updateDepositsInput.getAccountHolder().isBlank()
                || updateDepositsInput.getNameOfBank() == null || updateDepositsInput.getNameOfBank().isBlank()) {
            throw new GraphqlException("Account holder and name of bank are required");
        }
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposits not found for id: " + id));
        modelMapper.map(updateDepositsInput, deposits);
        Deposits updatedDeposits = depositsRepository.save(deposits);
        return modelMapper.map(updatedDeposits, DepositsDTO.class);
    }

    @Transactional
    public void deleteDeposit(Long id) {
        Deposits deposit = depositsRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Deposit not found for id: " + id));
        depositsRepository.delete(deposit);
    }

    @Transactional(readOnly = true)
    public PageOutput<DepositsDTO> getDepositsByStatus(PaymentStatus status, PagingInput pagingInput) {
        Page<Deposits> depositsPage = depositsRepository.findByStatusWithUser(status, PageUtil.buildPageable(pagingInput));
        if (depositsPage.isEmpty()) {
            throw new GraphqlException("Deposits not found for status: " + status);
        }
        return new PageOutput<>(
                depositsPage.getNumber(),
                depositsPage.getTotalPages(),
                depositsPage.getTotalElements(),
                depositsPage.getContent().stream()
                        .map(deposits -> modelMapperUtil.map(deposits, DepositsDTO.class))
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public PageOutput<DepositsDTO> getDepositByUserId(Long userId, PagingInput pagingInput) {
        userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        Page<Deposits> depositsPage = depositsRepository.findByUserIdWithUser(userId, PageUtil.buildPageable(pagingInput));
        return new PageOutput<>(
                depositsPage.getNumber(),
                depositsPage.getTotalPages(),
                depositsPage.getTotalElements(),
                depositsPage.getContent().stream()
                        .map(deposits -> modelMapperUtil.map(deposits, DepositsDTO.class))
                        .collect(Collectors.toList())
        );
    }
}
