package com.microcommerce.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange("paymentExchange");
    }
    @Bean
    public Queue notificationpaymentCompletedQueue() {
        return new Queue("notification-payment-completed-queue",true);
    }
    @Bean
    public Queue notificationpaymentFailedQueue() {
        return new Queue("notification-payment-failed-queue",true);
    }

    @Bean
    public Binding notificationpaymentCompletedBinding(Queue notificationpaymentCompletedQueue,DirectExchange  paymentExchange) {
        return BindingBuilder.bind(notificationpaymentCompletedQueue).to(paymentExchange).with("payment.completed");
    }

    @Bean
    public Binding  notificationpaymentFailedBinding(Queue notificationpaymentFailedQueue,DirectExchange  paymentExchange) {
        return BindingBuilder.bind(notificationpaymentFailedQueue).to(paymentExchange).with("payment.failed");
    }

}
