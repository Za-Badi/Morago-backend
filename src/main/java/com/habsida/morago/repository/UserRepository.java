package com.habsida.morago.repository;

import com.habsida.morago.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String userPhone);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.phone = :phone")
    Optional<User> findByPhoneWithRoles(@Param("phone") String phone);

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u JOIN u.translatorProfile tp JOIN tp.themes t " +
            "WHERE t.id = :themeId AND u.status = 'ONLINE' " +
            "ORDER BY u.ratings DESC")
    Optional<User> findAvailableTranslatorByThemeId(@Param("themeId") Long themeId);
}
