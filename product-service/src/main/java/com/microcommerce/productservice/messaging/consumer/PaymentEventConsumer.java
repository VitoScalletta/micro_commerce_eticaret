package com.microcommerce.productservice.messaging.consumer;

import com.microcommerce.productservice.dto.event.PaymentFailedEvent;
import com.microcommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final ProductService productService;

    @RabbitListener(queues = "product-payment-failed-queue")
    public void consumePaymentFailedEvent(PaymentFailedEvent event){
        try{
            System.out.println("Ödeme başarısız stok iade edilecek"+event.getOrderId());
            productService.restoreStock(event);
            System.out.println("Stok iadesi başarılı");
        }catch (Exception exception){
            System.out.println("İade edilirken hata oluştu"+exception.getMessage());
        }
    }

}
