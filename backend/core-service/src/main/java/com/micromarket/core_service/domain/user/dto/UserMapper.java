package com.micromarket.core_service.domain.user.dto;

import com.micromarket.common.generic.BaseDTOMapper;
import com.micromarket.core_service.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseDTOMapper<User, UserDTO> {
}
