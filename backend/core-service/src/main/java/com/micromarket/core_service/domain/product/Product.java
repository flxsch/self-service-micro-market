package com.micromarket.core_service.domain.product;

import com.micromarket.common.generic.ExtendedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product extends ExtendedEntity {
    @Getter
    @Setter
    @Column(name = "language", nullable = false)
    private String language;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "category", nullable = false)
    private String category;

    @Getter
    @Setter
    @Column(name = "stock", nullable = false)
    private Number stock;

    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private Number price;

    @Getter
    @Setter
    @Column(name = "barcode_id", nullable = false)
    private String barcodeId;

    public Product() {
        super();
    }
}