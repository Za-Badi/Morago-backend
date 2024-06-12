package com.habsida.morago.repository;

import com.habsida.morago.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepositoryPaged extends PagingAndSortingRepository<User, Long> {
}