package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedEntity;
import com.micromarket.core_service.domain.product.Product;
import com.micromarket.core_service.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order extends ExtendedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
