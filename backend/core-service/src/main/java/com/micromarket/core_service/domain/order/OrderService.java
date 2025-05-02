package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedService;
import com.micromarket.core_service.domain.product.Product;
import java.util.List;

public interface OrderService extends ExtendedService<Order> {
    Order findByPaymentId(String paymentId);
    List<Product> findProductsByOrderId(String orderId);
    List<Order> findOrdersByUserId(String userId);
}
