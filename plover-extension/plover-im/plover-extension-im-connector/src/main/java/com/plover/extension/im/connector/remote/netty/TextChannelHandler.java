package com.plover.extension.im.connector.remote.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/10 12:44
 */
@Slf4j
public class TextChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
        log.info("收到消息：{}",s);
    }
}
