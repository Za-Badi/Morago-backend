package com.habsida.morago.repository;

import com.habsida.morago.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String userPhone);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.phone = :phone")
    Optional<User> findByPhoneWithRoles(@Param("phone") String phone);
}
