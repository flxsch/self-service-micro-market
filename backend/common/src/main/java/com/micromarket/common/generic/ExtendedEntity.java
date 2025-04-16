package com.micromarket.common.generic;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Log4j2
@MappedSuperclass
public abstract class ExtendedEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Setter
    private Long createdByUserId;

    @Setter
    private Long updatedByUserId;

    @Setter
    @JoinColumn(updatable = false)
    protected LocalDateTime createdAt;

    @Setter
    @Column
    protected LocalDateTime updatedAt;
    protected ExtendedEntity() {
        this.id = UUID.randomUUID().toString();
    }
    public ExtendedEntity(String id) {
       this.id = id;
    }
    public ExtendedEntity setId(String id) {
        this.id = id;
        return this;
    }
}
