package com.micromarket.core_service.domain.product;

import com.micromarket.common.generic.ExtendedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends ExtendedEntity {
    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "stock", nullable = false)
    private Number stock;

    @Column(name = "price", nullable = false)
    private Number price;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    public Product() {
        super();
    }
}