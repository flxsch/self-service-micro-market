package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedJpaRepository;

import java.util.Optional;

public interface OrderRepository extends ExtendedJpaRepository<Order> {
    Optional<Order> findByPaymentId(String paymentId);
}
