package com.plover.extension.im.connector.processor;

import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.MessageDTO;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/21 17:38
 */
@Component
@Processor(type = IMActionType.HEART_BEAT)
public class HearthBeatProcessor implements MessageProcessor{

    @Override
    public void process(ChannelHandlerContext ctx, Object data) {
        //响应WebSocket心跳链接
        SendMessageUtils.send(ctx, MessageDTO.builder().action(IMActionType.HEART_BEAT.code()).data("success").build());
    }
}
