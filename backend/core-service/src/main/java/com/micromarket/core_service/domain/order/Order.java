package com.micromarket.core_service.domain.order;

import com.micromarket.common.generic.ExtendedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order extends ExtendedEntity {
    String userId;
}
