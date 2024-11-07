package com.plover.extension.im.cluster.configure;

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
@ConfigurationProperties(prefix = IMServerClusterConfigProperties.PRE)
public class IMServerClusterConfigProperties {
    public final static String PRE = "plover.im.cluster";

    /**
     * 服务id
     */
    private String serviceId;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 消息扩散队列名称
     */
    private String forwardChannelName;

    /**
     * 多端同步队列名称
     */
    private String multiDeviceChannelName;
}
