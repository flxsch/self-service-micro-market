package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedService;

public interface OrderService extends ExtendedService<Order> {
    Order findByPaymentId(String paymentId);
}
