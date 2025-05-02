package com.micromarket.core_service.domain.user;

import com.micromarket.common.generic.ExtendedService;

import java.util.NoSuchElementException;

public interface UserService extends ExtendedService<User> {
    User findByEmail(String email) throws NoSuchElementException;
}
