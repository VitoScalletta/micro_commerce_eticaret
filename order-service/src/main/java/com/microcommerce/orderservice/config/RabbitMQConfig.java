package com.microcommerce.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange("payment-exchange");
    }
    @Bean
    public Queue paymentCompleteQueue() {
        return new Queue("payment-completed-queue");
    }

    @Bean
    public Queue orderpaymentFailedQueue() {
        return new Queue("order-payment-failed-queue");
    }

    @Bean
    public Queue productPaymentFailedQueue() {
        return new Queue("product-payment-failed-queue");
    }
    @Bean
    public Binding orderPaymentFailedBinding(Queue orderPaymentFailedQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(orderPaymentFailedQueue).to(paymentExchange).with("payment.failed");
    }

    @Bean
    public Binding productPaymentFailedBinding(Queue productPaymentFailedQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(productPaymentFailedQueue).to(paymentExchange).with("payment.failed");
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue("order-created-queue",true);
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange("order-exchange");
    }

    @Bean
    public Binding orderCreatedBinding(Queue orderCreatedQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with("order.created");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
