package com.plover.extension.im.connector.remote.netty.tcp.coder;

import cn.hutool.json.JSONUtil;
import com.plover.extension.im.core.model.MessageDTO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/21 19:35
 */
public class MessageProtocolEncoder extends MessageToByteEncoder<MessageDTO<Object>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageDTO messageDTO, ByteBuf byteBuf) throws Exception {
        String content = JSONUtil.toJsonPrettyStr(messageDTO);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        // 写入长度
        byteBuf.writeLong(bytes.length);
        // 写入命令体
        byteBuf.writeBytes(bytes);
    }
}
