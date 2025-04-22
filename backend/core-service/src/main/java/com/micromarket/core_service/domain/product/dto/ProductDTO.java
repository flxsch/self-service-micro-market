package com.micromarket.core_service.domain.product.dto;

import com.micromarket.common.generic.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {
    private String name;
    private String category;
    private Integer stock;
    private Float price;
    private String barcode;
}
