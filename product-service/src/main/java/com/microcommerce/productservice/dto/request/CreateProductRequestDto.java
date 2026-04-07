package com.microcommerce.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class CreateProductRequestDto {
    @NotBlank(message = "İsim boş bırakılamaz")
    private String name;

    @Positive
    @NotNull(message = "Fiyat boş bırakılamaz")
    private BigDecimal price;

    @Positive
    @NotNull(message = "Stok miktarı boş bırakılamaz")
    private Integer stockQuantity;
}
