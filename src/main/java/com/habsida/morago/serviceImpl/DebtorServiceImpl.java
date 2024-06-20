package com.habsida.morago.serviceImpl;

import com.habsida.morago.exceptions.GraphqlException;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.model.inputs.CreateDebtorInput;
import com.habsida.morago.model.inputs.UpdateDebtorInput;
import com.habsida.morago.repository.DebtorRepository;
import com.habsida.morago.repository.UserRepository;
import com.habsida.morago.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtorServiceImpl implements DebtorService {
    private final DebtorRepository debtorRepository;
    private final UserRepository userRepository;

    @Autowired
    public DebtorServiceImpl(DebtorRepository debtorRepository, UserRepository userRepository) {
        this.debtorRepository = debtorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Debtor> getAllDebtors() {
        return debtorRepository.findAll();
    }

    @Override
    public Debtor getDebtorById(Long id) {
        return debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));
    }

    @Override
    public Debtor addDebtor(CreateDebtorInput createDebtorInput) {

        Debtor debtor = new Debtor();
        debtor.setAccountHolder(createDebtorInput.getAccountHolder());
        debtor.setNameOfBank(createDebtorInput.getNameOfBank());
        debtor.setIsPaid(createDebtorInput.getIsPaid());
        User user = userRepository.findById(createDebtorInput.getUserId())
                .orElseThrow(() -> new GraphqlException("User not found for id: " + createDebtorInput.getUserId()));
        debtor.setUser(user);

        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor updateDebtor(Long id, UpdateDebtorInput updateDebtorInput) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));

        if (updateDebtorInput.getAccountHolder() != null & !updateDebtorInput.getAccountHolder().isEmpty()) {
            debtor.setAccountHolder(updateDebtorInput.getAccountHolder());
        }
        if (updateDebtorInput.getNameOfBank() != null && !updateDebtorInput.getNameOfBank().isEmpty()) {
            debtor.setNameOfBank(updateDebtorInput.getNameOfBank());
        }
        if (updateDebtorInput.getIsPaid() != null) {
            debtor.setIsPaid(updateDebtorInput.getIsPaid());
        }

        return debtorRepository.save(debtor);
    }

    @Override
    public void deleteDebtor(Long id) {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new GraphqlException("Debtor not found for id: " + id));
        debtorRepository.deleteById(id);
    }

    @Override
    public List<Debtor> getDebtorByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GraphqlException("User not found for id: " + userId));
        List<Debtor> debtorByUserId = debtorRepository.findByUserId(userId);

        return debtorByUserId;
    }


}