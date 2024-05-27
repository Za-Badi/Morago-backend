package com.habsida.morago.service;


import com.habsida.morago.model.entity.PasswordReset;
import com.habsida.morago.repository.PasswordResetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final PasswordResetRepository repository;
    public PasswordReset create(PasswordReset passwordReset) {
        return repository.save(passwordReset);
    }

    public PasswordReset getById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public PasswordReset update(Long id, String phone, String token){
        PasswordReset passwordReset = getById(id);
        passwordReset.setPhone(phone);
        passwordReset.setToken(token);
        return repository.saveAndFlush(passwordReset);
    }

    public Boolean deleteById(Long id){
        repository.deleteById(id);
        return true;
    }
}
