package com.microcommerce.orderservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class CreateOrderRequestDto {
    @NotNull(message = "ProductId boş bırakılamaz")
    private Long productId;

    @NotNull(message = "StockQuantity boş bırakılamaz")
    private Integer stockQuantity;


}
