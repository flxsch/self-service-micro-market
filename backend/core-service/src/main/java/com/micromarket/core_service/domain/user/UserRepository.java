package com.micromarket.core_service.domain.user;

import com.micromarket.common.generic.ExtendedJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ExtendedJpaRepository<User> {
    Optional<User> findByEmail(String email);
}
