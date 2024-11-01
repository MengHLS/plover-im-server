package com.plover.exetension.im.server;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class IMServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMServerApplication.class, args);
    }

}
