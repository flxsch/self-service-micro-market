package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedServiceImpl;
import com.micromarket.core_service.domain.product.Product;
import org.springframework.stereotype.Service;
import com.micromarket.core_service.domain.orderproduct.OrderProduct;

import java.util.List;
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

    @Override
    public List<Product> findProductsByOrderId(String orderId) {
        Order order = findById(orderId);
        return order.getOrderProducts().stream()
                .map(OrderProduct::getProduct)
                .toList();
    }

    @Override
    public List<Order> findOrdersByUserId(String userId) {
        return ((OrderRepository) repository).findByUserId(userId);
    }
}
