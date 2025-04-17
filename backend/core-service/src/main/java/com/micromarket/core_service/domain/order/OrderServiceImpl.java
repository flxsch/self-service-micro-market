package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedServiceImpl;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl extends ExtendedServiceImpl<Order> implements OrderService {
    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }

    @Override
    public Order findByPaymentId(String paymentId) {
        return ((OrderRepository) repository).findByPaymentId(paymentId).orElseThrow(() -> new NoSuchElementException((String.format("Order with payment id: '%s' doesn't exist", paymentId))));
    }
}
