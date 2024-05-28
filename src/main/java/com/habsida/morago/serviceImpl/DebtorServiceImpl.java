package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.entity.Debtor;
import com.habsida.morago.repository.DebtorRepository;
import com.habsida.morago.service.DebtorService;
import com.habsida.morago.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtorServiceImpl implements DebtorService {

    @Autowired
    private DebtorRepository debtorRepository;
    private UserService userService;
    @Override
    public List<Debtor> getAllDebtors() {
        return debtorRepository.findAll();
    }

    @Override
    public Optional<Debtor> getDebtorById(Long id) {
        return debtorRepository.findById(id);
    }

    @Override
    public List<Debtor> getDebtorsByUserId(Long userId) {
        return debtorRepository.findByUserId(userId);
    }

    @Override
    public Debtor saveDebtor(Debtor debtor) {
        return debtorRepository.save(debtor);
    }

    @Override
    public Debtor updateDebtor(Long id, Debtor debtor) {
        Optional<Debtor> existingDebtor = debtorRepository.findById(id);
        if(existingDebtor.isPresent()){
            Debtor updatedDebtor = existingDebtor.get();
            updatedDebtor.setAccountHolder(debtor.getAccountHolder());
            updatedDebtor.setNameOfBank(debtor.getNameOfBank());
            updatedDebtor.setIsPaid(debtor.getIsPaid());
            updatedDebtor.setCreatedAt(debtor.getCreatedAt());
            updatedDebtor.setUpdatedAt(debtor.getUpdatedAt());
            return debtorRepository.save(updatedDebtor);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDebtor(Long id) {
        debtorRepository.deleteById(id);

    }
}
