package com.micromarket.core_service.domain.user.dto;

import com.micromarket.common.generic.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    protected String firstName;
    protected String lastName;
    protected String email;
}
