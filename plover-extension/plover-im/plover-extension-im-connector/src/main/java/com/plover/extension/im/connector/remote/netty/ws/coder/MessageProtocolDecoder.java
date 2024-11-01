package com.plover.extension.im.connector.remote.netty.ws.coder;

import cn.hutool.json.JSONUtil;
import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.MessageDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/8 1:58
 */
@Slf4j
public class MessageProtocolDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) {

        MessageDTO messageDTO = JSONUtil.parse(textWebSocketFrame.text()).toBean(MessageDTO.class);
        if (!Objects.equals(messageDTO.getAction(), IMActionType.HEART_BEAT.getCode())){
            log.info(textWebSocketFrame.text());
        }
        list.add(messageDTO);
    }
}
