package com.habsida.morago.serviceImpl;

import com.habsida.morago.model.dto.PasswordResetDTO;
import com.habsida.morago.model.entity.PasswordReset;
import com.habsida.morago.repository.PasswordResetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final PasswordResetRepository repository;
    private final ModelMapper modelMapper;

    public PasswordResetDTO create(PasswordResetDTO passwordResetDTO) {
        PasswordReset passwordReset = modelMapper.map(passwordResetDTO, PasswordReset.class);
        PasswordReset savedPasswordReset = repository.save(passwordReset);
        return modelMapper.map(savedPasswordReset, PasswordResetDTO.class);
    }

    public PasswordResetDTO getById(Long id) {
        PasswordReset passwordReset = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Password reset request not found"));
        return modelMapper.map(passwordReset, PasswordResetDTO.class);
    }

    public PasswordResetDTO update(Long id, String phone, String token) {
        PasswordReset passwordReset = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Password reset request not found"));
        passwordReset.setPhone(phone);
        passwordReset.setToken(token);
        PasswordReset updatedPasswordReset = repository.saveAndFlush(passwordReset);
        return modelMapper.map(updatedPasswordReset, PasswordResetDTO.class);
    }

    public Boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }
}
