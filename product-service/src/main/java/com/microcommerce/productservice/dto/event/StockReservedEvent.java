package com.microcommerce.productservice.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockReservedEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;

}
