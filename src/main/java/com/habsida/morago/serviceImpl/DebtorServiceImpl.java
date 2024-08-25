package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.dto.CallDTO;
import com.habsida.morago.model.dto.DebtorDTO;
import com.habsida.morago.model.entity.Call;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.PagingInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.model.results.PageOutput;
import com.habsida.morago.repository.DebtorRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DebtorService;
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
public class DebtorServiceImpl implements DebtorService {
    private final DebtorRepository debtorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional(readOnly = true)
    public PageOutput<DebtorDTO> getAllDebtors(PagingInput pagingInput) {
        Page<Debtor> debtors = debtorRepository.findAll(PageUtil.buildPageable(pagingInput));
        return new PageOutput<>(
                debtors.getNumber(),
                debtors.getTotalPages(),
                debtors.getTotalElements(),
                debtors.getContent().stream()
                        .map(withdrawals -> modelMapperUtil.map(debtors, DebtorDTO.class))
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public DebtorDTO getDebtorById(Long id) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));
        return modelMapper.map(debtor, DebtorDTO.class);
    }

    @Transactional
    public DebtorDTO addDebtor(CreateDebtorInput createDebtorInput) {
        Debtor debtor = new Debtor();
        if (createDebtorInput.getAccountHolder() == null || createDebtorInput.getAccountHolder().isBlank()
        || createDebtorInput.getNameOfBank() == null || createDebtorInput.getNameOfBank().isBlank()
        || createDebtorInput.getIsPaid() == null) {
            throw new GraphqlException("Account holder and name of bank are required");
        }

        User user = userRepository.findById(createDebtorInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createDebtorInput.getUserId()));

        if (user.getIsDebtor()) {
            throw new GraphqlException("Account holder is already debtor");
        }

        debtor.setAccountHolder(createDebtorInput.getAccountHolder());
        debtor.setNameOfBank(createDebtorInput.getNameOfBank());
        debtor.setIsPaid(createDebtorInput.getIsPaid());
        debtor.setUser(user);

        if (!debtor.getIsPaid()) {
            user.setIsDebtor(true);
            userRepository.save(user);
        }

        Debtor savedDebtor = debtorRepository.save(debtor);
        return modelMapper.map(savedDebtor, DebtorDTO.class);
    }

    @Transactional
    public DebtorDTO updateDebtor(Long id, UpdateDebtorInput updateDebtorInput) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));

        if (updateDebtorInput.getAccountHolder() != null && !updateDebtorInput.getAccountHolder().isBlank()) {
            debtor.setAccountHolder(updateDebtorInput.getAccountHolder());
        }
        if (updateDebtorInput.getNameOfBank() != null && !updateDebtorInput.getNameOfBank().isBlank()) {
            debtor.setNameOfBank(updateDebtorInput.getNameOfBank());
        }
        if (updateDebtorInput.getIsPaid() != null) {
            debtor.setIsPaid(updateDebtorInput.getIsPaid());
        }

        if (debtor.getIsPaid()) {
            User debtorUser = debtor.getUser();
            debtorUser.setIsDebtor(false);
            userRepository.save(debtorUser);
        }
        Debtor updatedDebtor = debtorRepository.save(debtor);
        return modelMapper.map(updatedDebtor, DebtorDTO.class);
    }

    @Transactional
    public void deleteDebtor(Long id) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));
        debtorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DebtorDTO> getDebtorByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Debtor> debtors = debtorRepository.findByUserId(userId);
        return debtors.stream()
                .map(debtor -> modelMapper.map(debtor, DebtorDTO.class))
                .collect(Collectors.toList());
    }
}
