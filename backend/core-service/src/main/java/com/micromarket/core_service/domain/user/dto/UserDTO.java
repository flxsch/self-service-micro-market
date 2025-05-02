package com.micromarket.core_service.domain.user.dto;

import com.micromarket.common.generic.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String language;
    @Nullable
    private String keycloakId;
}