package com.microcommerce.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

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
    public Queue orderCreatedQueue() {
        return new Queue("order-created-queue",true);
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange("order-exchange");
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Binding paymentCompleteBinding(@Qualifier("paymentCompleteQueue") Queue paymentCompleteQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(paymentCompleteQueue).to(paymentExchange).with("payment.completed");
    }
    @Bean
    public Binding orderCreatedBinding(@Qualifier("orderCreatedQueue")Queue orderCreatedQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with("order.created");
    }
    @Bean
    public Binding productPaymentFailedBinding(@Qualifier("productPaymentFailedQueue")Queue productPaymentFailedQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(productPaymentFailedQueue).to(paymentExchange).with("payment.failed");
    }
    @Bean
    public Binding orderPaymentFailedBinding(@Qualifier("orderpaymentFailedQueue") Queue orderPaymentFailedQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(orderPaymentFailedQueue).to(paymentExchange).with("payment.failed");
    }

}
