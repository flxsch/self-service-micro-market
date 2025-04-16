package com.micromarket.common.generic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtendedJpaRepository<T extends ExtendedEntity> extends JpaRepository<T, String> {

}