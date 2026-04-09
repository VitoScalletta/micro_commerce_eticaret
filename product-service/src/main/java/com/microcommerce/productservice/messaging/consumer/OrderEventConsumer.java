package com.microcommerce.productservice.messaging.consumer;

import com.microcommerce.productservice.dto.event.OrderCreatedEvent;
import com.microcommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final ProductService productService;

    @RabbitListener(queues = "order-created-queue")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Order Created Event ürün ID: "+event.getProductId());
        productService.reduceStock(event.getProductId(), event.getQuantity());
    }
}
