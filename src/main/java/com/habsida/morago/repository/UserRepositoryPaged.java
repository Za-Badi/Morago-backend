package com.habsida.morago.repository;

import com.habsida.morago.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepositoryPaged extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.phone) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchInput, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchInput, '%'))")
    Page<User> searchUsersByPhoneOrFirstNameOrLastName(
            String searchInput,
            Pageable pageable
    );
}