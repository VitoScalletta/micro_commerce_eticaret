package com.microcommerce.orderservice.controller;

import com.microcommerce.orderservice.dto.request.CreateOrderRequestDto;
import com.microcommerce.orderservice.dto.response.OrderResponseDto;
import com.microcommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody CreateOrderRequestDto createOrderRequestDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(createOrderRequestDto));
    }
}
