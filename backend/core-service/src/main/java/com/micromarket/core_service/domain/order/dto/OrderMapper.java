package com.micromarket.core_service.domain.order.dto;

import com.micromarket.common.generic.BaseDTOMapper;
import com.micromarket.core_service.domain.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends BaseDTOMapper<Order, OrderDTO> {
}
