package com.micromarket.core_service.domain.product.dto;

import com.micromarket.common.generic.BaseDTOMapper;
import com.micromarket.core_service.domain.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends BaseDTOMapper<Product, ProductDTO> {
}
