package com.microcommerce.orderservice.publisher;

import com.microcommerce.orderservice.dto.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend("order-exchange", "order.created", event);
        System.out.println("OrderCreated event published: " + event.getProductId() + "Numaralı ürün satıldı");
    }

}
