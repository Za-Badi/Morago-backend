package com.habsida.morago.repository;

import com.habsida.morago.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String userPhone);
}
