package com.microcommerce.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
}
