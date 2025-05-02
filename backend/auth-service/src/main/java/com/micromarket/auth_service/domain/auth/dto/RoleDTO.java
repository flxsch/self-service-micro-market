package com.micromarket.auth_service.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String id;
    private String name;
    private String description;
    private boolean composite;
    private boolean clientRole;
    private String containerId;
}

