package com.microcommerce.paymentservice.messaging.consumer;

import com.microcommerce.paymentservice.dto.event.StockReservedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockReservedEventConsumer {

    @RabbitListener(queues = "stock-reserved-queue")
    public void StockReservedEvent(StockReservedEvent event){
        System.out.println("Veznedar mektubu aldı! Sipariş ID: " + event.getOrderId() + " | Müşteri ID: " + event.getUserId() + " | Tutar: " + event.getTotalPrice());}
}
