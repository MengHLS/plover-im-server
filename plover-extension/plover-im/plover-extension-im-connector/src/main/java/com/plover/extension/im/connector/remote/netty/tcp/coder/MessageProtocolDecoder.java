package com.plover.extension.im.connector.remote.netty.tcp.coder;

import cn.hutool.json.JSONUtil;
import com.plover.extension.im.core.model.MessageDTO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/21 19:29
 */
public class MessageProtocolDecoder extends ByteToMessageDecoder {
    private static final Logger log = LoggerFactory.getLogger(MessageProtocolDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        // 获取到包的长度
        int length = byteBuf.readableBytes();
        // 转成IMSendInfo

        String content = byteBuf.readBytes( length).toString(CharsetUtil.UTF_8);
        log.info("客户端发来消息{}", content);
        list.add(JSONUtil.parseObj(content).toBean(MessageDTO.class));
    }

}
