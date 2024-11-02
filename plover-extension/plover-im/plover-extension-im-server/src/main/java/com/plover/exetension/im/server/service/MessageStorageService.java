package com.plover.exetension.im.server.service;

import com.plover.extension.im.core.model.BaseMessage;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/27 21:28
 */
public class MessageStorageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private Queue storageQueue;

    /**
     * 发送消息
     * @param message 消息
     */
    public void sendMessage(BaseMessage message) {
        rabbitTemplate.convertAndSend(storageQueue.getName(), message);
    }
}
