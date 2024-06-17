package com.habsida.morago.repository;

import com.habsida.morago.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepositoryPaged extends PagingAndSortingRepository<User, Long> {
    Page<User> findByPhoneContainingAndFirstNameContainingIgnoreCase(String phone, String firstName, Pageable pageable);
}