package com.micromarket.core_service.domain.user;

import com.micromarket.common.generic.ExtendedJpaRepository;
import com.micromarket.common.generic.ExtendedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

    protected UserServiceImpl(ExtendedJpaRepository<User> repository) {
        super(repository);
    }
}
