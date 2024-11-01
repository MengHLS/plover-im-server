package com.plover.extension.im.connector.remote.netty.ws.coder;

import cn.hutool.json.JSONUtil;
import com.plover.extension.im.core.model.MessageDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/8 2:29
 */
public class MessageProtocolEncoder extends MessageToMessageEncoder<MessageDTO<Object>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageDTO<Object> messageDTO, List<Object> list) {
        String text = JSONUtil.toJsonPrettyStr(messageDTO);

        TextWebSocketFrame frame = new TextWebSocketFrame(text);
        list.add(frame);
    }
}
