package com.microcommerce.paymentservice.config;

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
    public DirectExchange productExchange() {
        return new DirectExchange("productExchange");
    }

    @Bean
    public Queue stockReservedQueue() {
        return new Queue("stock-reserved-queue");
    }

    @Bean
    public Binding stockReservedBinding(Queue stockReservedQueue, DirectExchange productExchange) {
        return BindingBuilder.bind(stockReservedQueue).to(productExchange).with("stock.reserved");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
