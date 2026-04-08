package com.microcommerce.orderservice.dto.response;

import com.microcommerce.orderservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
}
