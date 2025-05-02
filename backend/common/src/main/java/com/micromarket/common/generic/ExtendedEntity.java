package com.micromarket.common.generic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Log4j2
@MappedSuperclass
public abstract class ExtendedEntity {
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name="created_by", nullable = false, updatable = false)
    private String createdByUserId;

    @Column(name="last_modified_by")
    private String lastModifiedBy;

    @Column(name="created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name="updated_at")
    protected LocalDateTime updatedAt;
}
