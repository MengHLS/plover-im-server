package com.plover.extension.im.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/30 9:48
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class IMConsumerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(IMConsumerApplication.class, args);
    }
}
