package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedEntity;
import com.micromarket.core_service.domain.OrderProduct;
import com.micromarket.core_service.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends ExtendedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "payment_id")
    private String paymentId;
}
