package com.microcommerce.paymentservice.service;

import com.microcommerce.paymentservice.dto.event.PaymentCompletedEvent;
import com.microcommerce.paymentservice.dto.event.PaymentFailedEvent;
import com.microcommerce.paymentservice.dto.event.StockReservedEvent;
import com.microcommerce.paymentservice.entity.UserBalance;
import com.microcommerce.paymentservice.messaging.publisher.PaymentEventPublisher;
import com.microcommerce.paymentservice.repository.UserBalanceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserBalanceRepository userBalanceRepository;
    private final PaymentEventPublisher  paymentEventPublisher;

    @Transactional
    public void processPayment(StockReservedEvent event){
        UserBalance userBalance = userBalanceRepository.findByUserId(event.getUserId())
                .orElseThrow(()->new RuntimeException("UserBalance Not Found"));

        if (userBalance.getBalance().compareTo(event.getTotalPrice())>=0){
            userBalance.setBalance(userBalance.getBalance().subtract(event.getTotalPrice()));
            userBalanceRepository.save(userBalance);
            System.out.println("Payment Completed");
            PaymentCompletedEvent completedEvent = new PaymentCompletedEvent(event.getOrderId());
            paymentEventPublisher.publishPaymentCompletedEvent(completedEvent);
        }
        else{
            System.out.println("Payment Failed : Yetersiz bakiye Sipariş id : "+ event.getOrderId());
            PaymentFailedEvent failedEvent = new PaymentFailedEvent(event.getOrderId(),1L,2);
            paymentEventPublisher.publishPaymentCompletedEvent(failedEvent);


        }
    }
}
