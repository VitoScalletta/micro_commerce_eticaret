package com.microcommerce.orderservice.consumer;

import com.microcommerce.orderservice.dto.event.PaymentCompletedEvent;
import com.microcommerce.orderservice.dto.event.PaymentFailedEvent;
import com.microcommerce.orderservice.entity.Order;
import com.microcommerce.orderservice.entity.OrderStatus;
import com.microcommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventConsumer {
    private final OrderRepository orderRepository;

    @RabbitListener(queues = "payment-completed-queue")
    public void consumePaymentCompletedEvent(PaymentCompletedEvent event) {
        System.out.println("Received Payment Completed Event"+event.getOrderId());

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        System.out.println("SAGA finito resultante importante si?");
    }

    @RabbitListener(queues = "payment-failed-queue")
    public void consumePaymentFailedEvent(PaymentFailedEvent event) {
        System.out.println("Received Payment Failed Event"+event.getOrderId());

        Order order =  orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        System.out.println("Sipariş iptal Edildi ");
    }
}
