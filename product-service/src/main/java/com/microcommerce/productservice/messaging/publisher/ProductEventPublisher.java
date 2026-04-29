package com.microcommerce.productservice.messaging.publisher;

import com.microcommerce.productservice.dto.event.StockReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishStockReservedEvent(StockReservedEvent event) {
        rabbitTemplate.convertAndSend("productExchange","stock.reserved",event);
        System.out.println("Stock Reserved Event Published");
    }
}
