package com.microcommerce.orderservice.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private Long productId;
    private Long userId;
    private Long orderId;
    private BigDecimal totalPrice;
    private Integer quantity;

}
