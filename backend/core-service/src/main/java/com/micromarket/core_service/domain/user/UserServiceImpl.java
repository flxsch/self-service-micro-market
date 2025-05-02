package com.micromarket.core_service.domain.user;

import com.micromarket.common.generic.ExtendedServiceImpl;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {
    private final UserRepository userRepository;

    protected UserServiceImpl(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    @Override
    public User findByEmail(String email) throws NoSuchElementException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, email));
        }
        return user.get();
    }
}
