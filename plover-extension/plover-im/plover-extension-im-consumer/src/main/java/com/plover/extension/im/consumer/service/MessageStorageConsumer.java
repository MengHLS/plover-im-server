package com.plover.extension.im.consumer.service;

import com.plover.extension.im.core.model.BaseMessage;
import com.ruoyi.common.mongodb.service.MongoService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/30 10:48
 */
@RabbitListener(queues = "StorageQueue")
public class MessageStorageConsumer {

    @Autowired
    private MongoService mongoService;


    @RabbitHandler
    public void receiveMessage(BaseMessage message) {

        mongoService.insert(message,"MessageStorage");
        mongoService.insert(message,"OfflineMessageStorage");
    }
}
