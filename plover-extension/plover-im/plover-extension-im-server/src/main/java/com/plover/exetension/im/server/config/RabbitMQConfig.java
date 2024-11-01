package com.plover.exetension.im.server.config;

import com.plover.exetension.im.server.service.MessageStorageService;
import org.springframework.amqp.core.Queue;
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
    public Queue storageQueue() {
        return new Queue("StorageQueue");
    }

    @Bean
    public MessageStorageService sender(){
        return new MessageStorageService();
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
