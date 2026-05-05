package com.microcommerce.notificationservice.messaging.consumer;

import com.microcommerce.notificationservice.dto.event.PaymentCompletedEvent;
import com.microcommerce.notificationservice.dto.event.PaymentFailedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = "notification-payment-completed-queue")
    public void sendSuccessEmail(PaymentCompletedEvent event) {
        System.out.println("------------------------");
        System.out.println("---Gönderilen E-posta---");
        System.out.println("Kime : "+event.getUserId());
        System.out.println(event.getOrderId()+"numaralı siparişin ödemesi başarıyla alındı");
        System.out.println("------------------------");
    }

    @RabbitListener(queues = "notification-payment-failed-queue")
    public void sendFailureEmail(PaymentFailedEvent event) {
        System.out.println("-----------------------");
        System.out.println("---Gönderilen E-posta---");
        System.out.println("Kime : "+event.getUserId());
        System.out.println(event.getOrderId()+"numaralı siparişin ödemesi alınamadı");
        System.out.println("-------------------------");
    }
}
