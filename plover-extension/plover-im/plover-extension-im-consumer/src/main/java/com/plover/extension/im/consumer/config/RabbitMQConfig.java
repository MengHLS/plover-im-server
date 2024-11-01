package com.plover.extension.im.consumer.config;

import com.plover.extension.im.consumer.service.MessageStorageConsumer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:
 * @author: plover
 * @date: 2024/10/27 21:23
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageStorageConsumer receiver(){
        return new MessageStorageConsumer();
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
