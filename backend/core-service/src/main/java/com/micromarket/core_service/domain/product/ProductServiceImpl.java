package com.micromarket.core_service.domain.product;

import com.micromarket.common.generic.ExtendedJpaRepository;
import com.micromarket.common.generic.ExtendedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ExtendedServiceImpl<Product> implements ProductService {

    protected ProductServiceImpl(ExtendedJpaRepository<Product> repository) {
        super(repository);
    }
}
