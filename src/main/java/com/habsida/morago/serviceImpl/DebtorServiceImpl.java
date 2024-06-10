package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.input.DebtorInput;
import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.model.entity.User;
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
    public Debtor getDebtorById(Long id) throws Exception {
        return debtorRepository.findById(id)
                .orElseThrow(() -> new Exception("Debtor not found for id: " + id));
    }

    @Override
    public Debtor addDebtor(DebtorInput debtorDto) {
        User user = userRepository.findById(debtorDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + debtorDto.getUserId()));

        Debtor debtor = new Debtor();
        debtor.setAccountHolder(debtorDto.getAccountHolder());
        debtor.setNameOfBank(debtorDto.getNameOfBank());
        debtor.setIsPaid(debtorDto.getIsPaid());
        debtor.setUser(user);

        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor updateDebtor(Long id, DebtorInput debtorDto) throws Exception {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new Exception("Debtor not found for id: " + id));
        debtor.setAccountHolder(debtorDto.getAccountHolder());
        debtor.setNameOfBank(debtorDto.getNameOfBank());
        debtor.setIsPaid(debtorDto.getIsPaid());

        return debtorRepository.save(debtor);
    }

    @Override
    public void deleteDebtor(Long id) throws Exception {
        Debtor debtor = debtorRepository.findById(id)
                .orElseThrow(() -> new Exception("Debtor not found for id: " + id));
        debtorRepository.delete(debtor);
    }
}