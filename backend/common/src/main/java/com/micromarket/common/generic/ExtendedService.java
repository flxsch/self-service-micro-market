package com.micromarket.common.generic;

import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ExtendedService<T extends ExtendedEntity> {

    T save(T entity);

    T updateById(String id, T entity) throws BadRequestException;

    void deleteById(String id);

    List<T> findAll();

    T findById(String id);

    boolean existsById(String id);
}
