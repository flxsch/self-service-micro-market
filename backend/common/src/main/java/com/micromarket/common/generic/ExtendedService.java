package com.micromarket.common.generic;

import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.NoSuchElementException;

public interface ExtendedService<T extends ExtendedEntity> {

    T save(T entity);

    T updateById(String id, T entity) throws BadRequestException;

    void deleteById(String id) throws NoSuchElementException;

    List<T> findAll();

    T findById(String id) throws NoSuchElementException;

    boolean existsById(String id);
}
