package com.micromarket.common.generic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Log4j2
@MappedSuperclass
public abstract class ExtendedEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column
    private String createdByUserId;

    @Column
    private String updatedByUserId;

    @JoinColumn(updatable = false)
    protected LocalDateTime createdAt;

    @Column
    protected LocalDateTime updatedAt;
}
