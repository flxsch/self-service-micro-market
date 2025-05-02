package com.micromarket.auth_service.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String language;
    @Nullable
    private String keycloakId;
}
