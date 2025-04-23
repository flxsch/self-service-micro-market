package com.micromarket.common.generic;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class ExtendedServiceImpl<T extends ExtendedEntity> implements ExtendedService<T> {

    protected static final String NO_SUCH_ELEMENT_ERROR_MSG = "Entity with ID '%s' could not be found";
    protected final ExtendedJpaRepository<T> repository;

    protected Logger LOGGER = LoggerFactory.getLogger(ExtendedServiceImpl.class);

    protected ExtendedServiceImpl(ExtendedJpaRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) throws NoSuchElementException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public T updateById(String id, T entity) throws BadRequestException {
        if (repository.existsById(id)) {
            checkUpdatedEntityId(id, entity);

            entity.setId(id);
            return repository.save(entity);
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(String id) throws NoSuchElementException {
        Optional<T> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    protected void checkUpdatedEntityId(String id, T entity) throws BadRequestException {
        LOGGER.debug("id: {}", id);
        LOGGER.debug("entity: {}", entity);

        if (entity.getId() != null) {
            if (id.equals(entity.getId())) {
                return;
            }
            throw new BadRequestException(
                    String.format("Path variable ID '%s' and Request body ID '%s' are not equal", id, entity.getId()));
        }
    }

}

