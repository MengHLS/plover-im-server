package com.plover.extension.im.cluster.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: plover
 * @date: 2024/11/7 12:47
 */
@Slf4j
@Component
public class MultiDeviceListener implements MessageListener {
    /**
     * Callback for processing received objects through Redis.
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info( message.toString());
    }
}
