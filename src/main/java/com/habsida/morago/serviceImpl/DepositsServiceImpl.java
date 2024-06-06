
package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.inputs.DepositsInput;
import com.habsida.morago.model.entity.Deposits;
import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.DepositsRepository;
import com.habsida.morago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositsServiceImpl implements com.habsida.morago.services.DepositsService {
    private final DepositsRepository depositsRepository;
    private final UserRepository userRepository;

    @Autowired
    public DepositsServiceImpl(DepositsRepository depositsRepository, UserRepository userRepository) {
        this.depositsRepository = depositsRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Deposits> getAllDeposits() {
        return depositsRepository.findAll();
    }

    public Deposits getDepositById(Long id) throws Exception {
        return depositsRepository.findById(id)
                .orElseThrow(()-> new Exception("Deposits not found for id: " + id));
    }

    public Deposits addDeposit(DepositsInput depositsDto) {
        User user = userRepository.findById(depositsDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Deposits deposits = new Deposits();
        deposits.setAccountHolder(depositsDto.getAccountHolder());
        deposits.setNameOfBank(depositsDto.getNameOfBank());
        deposits.setWon(depositsDto.getWon());
        deposits.setCoin(depositsDto.getCoin());
        deposits.setStatus(depositsDto.getStatus());
        deposits.setUser(user);

        return depositsRepository.save(deposits);
    }

    public Deposits updateDeposit(Long id, DepositsInput depositsDto) throws Exception {
        Deposits deposits = depositsRepository.findById(id)
                .orElseThrow(() -> new Exception("Deposits not found for id: " + id));
        if (depositsDto.getAccountHolder() != null) {
            deposits.setAccountHolder(depositsDto.getAccountHolder());
        }
        if (depositsDto.getNameOfBank() != null) {
            deposits.setNameOfBank(depositsDto.getNameOfBank());
        }
        if (depositsDto.getNameOfBank() != null) {
            deposits.setNameOfBank(depositsDto.getNameOfBank());
        }
        if (depositsDto.getCoin() != null) {
            deposits.setCoin(depositsDto.getCoin());
        }
        if (depositsDto.getWon() != null) {
            deposits.setWon(depositsDto.getWon());
        }if (depositsDto.getStatus() != null) {
            deposits.setStatus(depositsDto.getStatus());
        }
        if (depositsDto.getUserId() != null) {
            User user = userRepository.findById(depositsDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            deposits.setUser(user);
        }

        return depositsRepository.save(deposits);
    }

    public void deleteDeposit(Long id) throws Exception {
        depositsRepository.findById(id)
                .orElseThrow(() -> new Exception("Deposits not found for id: " + id));
        depositsRepository.deleteById(id);
    }
}
