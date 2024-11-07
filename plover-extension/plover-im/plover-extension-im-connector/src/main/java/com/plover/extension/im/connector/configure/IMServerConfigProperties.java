package com.plover.extension.im.connector.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 长连接配置类
 * @author: plover
 * @date: 2024/8/8 0:58
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = IMServerConfigProperties.PRE)
public class IMServerConfigProperties {
    public final static String PRE = "plover.im";

    /**
     * 公网ip
     */
    private String ip;

    private TcpNode ws;

    private TcpNode tcp;

    private String serviceId;

    @Setter
    @Getter
    public static class TcpNode {

        private Boolean enable;

        /**
         * -1随机
         */
        private Integer port;

    }

}
