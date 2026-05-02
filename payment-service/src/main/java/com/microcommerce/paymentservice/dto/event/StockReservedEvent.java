package com.microcommerce.paymentservice.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockReservedEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private Long productId;
    private Integer quantity;
}
