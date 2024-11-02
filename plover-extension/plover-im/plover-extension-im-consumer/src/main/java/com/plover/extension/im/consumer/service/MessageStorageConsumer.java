package com.plover.extension.im.consumer.service;

import com.plover.extension.im.core.constant.MQConstant;
import com.plover.extension.im.core.constant.MongoConstant;
import com.plover.extension.im.core.model.BaseMessage;
import com.ruoyi.common.mongodb.service.MongoService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


/**
 * @description:
 * @author: plover
 * @date: 2024/10/30 10:48
 */
@RabbitListener(queues = MQConstant.MQ_STORAGE_QUEUE_NAME, ackMode = "AUTO")
public class MessageStorageConsumer {

    @Autowired
    private MongoService mongoService;


    @RabbitHandler
    public void receiveMessage(BaseMessage message) {

        mongoService.insert(message, MongoConstant.MESSAGE_STORAGE_CONNECTION_NAME);
        mongoService.insert(message,MongoConstant.OFFLINE_MESSAGE_STORAGE_CONNECTION_NAME);
    }
}
