package com.microcommerce.paymentservice.messaging.publisher;

import com.microcommerce.paymentservice.dto.event.PaymentCompletedEvent;
import com.microcommerce.paymentservice.dto.event.PaymentFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishPaymentCompletedEvent(PaymentCompletedEvent event) {
        rabbitTemplate.convertAndSend("payment-exchange", "payment.failed", event);
        System.out.println("Payment Completed Event: " + event);
    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {
        rabbitTemplate.convertAndSend("payment-exchange", "payment.failed", event);
        System.out.println("Payment Failed Event: " + event);
    }
}
