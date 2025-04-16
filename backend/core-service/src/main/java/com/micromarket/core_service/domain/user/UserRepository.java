package com.micromarket.core_service.domain.user;

import com.micromarket.common.generic.ExtendedJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ExtendedJpaRepository<User> {
    
}
