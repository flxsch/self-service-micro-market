package com.micromarket.auth_service.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private String access_token;
    private String refresh_token;
    private String id_token; // for google oauth
}
