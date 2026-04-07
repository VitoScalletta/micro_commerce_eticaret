package com.microcommerce.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class CreateProductRequestDto {
    @NotBlank(message = "İsim boş bırakılamaz")
    String name;

    @NotBlank(message = "Fiyat boş bırakılamaz")
    BigDecimal price;

    @NotBlank(message = "Stok miktarı boş bırakılamaz")
    Integer stockQuantity;
}
