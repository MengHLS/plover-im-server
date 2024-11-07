package com.plover.extension.im.cluster.configure;

import com.plover.extension.im.cluster.listener.ForwardMessageListener;
import com.plover.extension.im.cluster.listener.MultiDeviceListener;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @description:
 * @author: plover
 * @date: 2024/11/7 12:35
 */
@RequiredArgsConstructor
@Configuration
public class MessageListenerConfig {

    @Resource
    private IMServerClusterConfigProperties imServerClusterConfigProperties;

    @Resource
    private final ForwardMessageListener forwardMessageListener;

    @Resource
    private final MultiDeviceListener multiDeviceListener;


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(forwardMessageListener, new PatternTopic(imServerClusterConfigProperties.getForwardChannelName()));
        container.addMessageListener(multiDeviceListener, new PatternTopic(imServerClusterConfigProperties.getMultiDeviceChannelName()));
        return container;
    }
}
