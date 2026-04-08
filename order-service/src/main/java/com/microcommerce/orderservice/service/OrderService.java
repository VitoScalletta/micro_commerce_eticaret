package com.microcommerce.orderservice.service;

import com.microcommerce.orderservice.dto.request.CreateOrderRequestDto;
import com.microcommerce.orderservice.dto.response.OrderResponseDto;
import com.microcommerce.orderservice.entity.Order;
import com.microcommerce.orderservice.entity.OrderStatus;
import com.microcommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {
        Order order = modelMapper.map(requestDto, Order.class);
        order.setOrderStatus(OrderStatus.PENDING);

        BigDecimal fakePrice = BigDecimal.valueOf(100);
        BigDecimal quantity = BigDecimal.valueOf(requestDto.getStockQuantity());
        order.setTotalPrice(fakePrice.multiply(quantity));

        Order createdOrder = orderRepository.save(order);
        OrderResponseDto responseDto = modelMapper.map(createdOrder, OrderResponseDto.class);
        return modelMapper.map(createdOrder, OrderResponseDto.class);
    }

}
