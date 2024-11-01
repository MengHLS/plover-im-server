package com.plover.exetension.im.server.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.plover.exetension.im.server.service.ForwardMessageService;
import com.plover.exetension.im.server.service.MessageStorageService;
import com.plover.extension.im.connector.processor.MessageProcessor;
import com.plover.extension.im.connector.processor.Processor;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.BaseMessage;
import com.plover.extension.im.core.model.MessageDTO;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 单聊消息处理器
 * @author: plover
 * @date: 2024/10/26 22:04
 */
@Slf4j
@Component
@Processor(type = IMActionType.PRIVATE_MESSAGE)
public class PrivateMessageProcessor implements MessageProcessor {

    @Autowired
    private MessageStorageService storageService;

    @Autowired
    private ForwardMessageService forwardMessageService;
    /**
     * 单聊消息处理
     * @param ctx
     * @param data
     */
    @Override
    public void process(ChannelHandlerContext ctx, Object data) {
        BaseMessage baseMessage =  JSONUtil.toBean(data.toString(), BaseMessage.class);
        //1.处理消息
        baseMessage.setId(UUID.fastUUID().toString());
        baseMessage.setTimeStamp(DateUtil.current());
        baseMessage.setCreateTime(DateUtil.now());

        //1.查看对方是否在线，若在线，向对方推送消息，
        sendToReceiver(baseMessage);
        //2.离线消息推送
        storageService.sendMessage(baseMessage);

        //3.在线消息发送
        //3.1多端消息推送
        //发送消息回执
        SendMessageUtils.send(ctx, MessageDTO.builder().action(IMActionType.RECEIPT_MESSAGE.getCode()).data(baseMessage).build());
    }


    /**
     * 发送消息到接收人
     * @param baseMessage 消息
     */
    private void sendToReceiver(BaseMessage baseMessage) {
        MessageDTO<Object> messageDTO = new MessageDTO<>();
        messageDTO.setAction(IMActionType.PRIVATE_MESSAGE.getCode());
        messageDTO.setData(baseMessage);

        forwardMessageService.forwardMessageToPerson(messageDTO, baseMessage.getReceiverId());
    }

}
