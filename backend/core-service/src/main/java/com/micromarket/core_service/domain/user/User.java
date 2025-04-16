package com.micromarket.core_service.domain.user;

import com.micromarket.common.generic.ExtendedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User extends ExtendedEntity {
    @Getter
    @Setter
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Getter
    @Setter
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Getter
    @Setter
    @Column(name = "birth_date")
    private LocalDate birthDate;
    public User() {
        super();
    }
}