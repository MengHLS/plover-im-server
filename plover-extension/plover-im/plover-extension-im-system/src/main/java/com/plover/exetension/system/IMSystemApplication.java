package com.plover.exetension.system;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/23 18:32
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IMSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(IMSystemApplication.class, args);
    }
}
