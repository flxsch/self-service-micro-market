package com.micromarket.core_service.domain.product;

import com.micromarket.common.generic.ExtendedJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ExtendedJpaRepository<Product> {
    
}
