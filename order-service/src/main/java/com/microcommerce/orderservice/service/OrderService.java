package com.microcommerce.orderservice.service;

import com.microcommerce.orderservice.client.ProductServiceClient;
import com.microcommerce.orderservice.dto.event.OrderCreatedEvent;
import com.microcommerce.orderservice.dto.request.CreateOrderRequestDto;
import com.microcommerce.orderservice.dto.response.OrderResponseDto;
import com.microcommerce.orderservice.entity.Order;
import com.microcommerce.orderservice.entity.OrderStatus;
import com.microcommerce.orderservice.publisher.OrderEventPublisher;
import com.microcommerce.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    private final OrderEventPublisher orderEventPublisher;
    private final ProductServiceClient productServiceClient;
    @CircuitBreaker(name = "productServiceBreaker",fallbackMethod = "fallbackCreateOrder")
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {
        Order order = modelMapper.map(requestDto, Order.class);
        order.setId(null);
        order.setOrderStatus(OrderStatus.PENDING);

        BigDecimal fakePrice = BigDecimal.valueOf(100);
        BigDecimal quantity = BigDecimal.valueOf(requestDto.getQuantity());
        order.setTotalPrice(fakePrice.multiply(quantity));

        Order createdOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                createdOrder.getId(),
                createdOrder.getUserId(),
                createdOrder.getProductId(),
                createdOrder.getTotalPrice(),
                createdOrder.getQuantity()
        );
        orderEventPublisher.publishOrderCreatedEvent(event);
        return modelMapper.map(createdOrder, OrderResponseDto.class);
    }

    public OrderResponseDto fallbackCreateOrder(CreateOrderRequestDto requestDto,Exception exception) {
        System.out.println("Product service gg hata : "+exception.getMessage());
        throw new RuntimeException("Ürün servislerine ulaşılamıyor.Lütfen daha sonra tekrar deneyiniz");
    }
}
