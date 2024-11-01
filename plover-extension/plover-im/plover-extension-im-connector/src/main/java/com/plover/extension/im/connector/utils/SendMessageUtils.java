package com.plover.extension.im.connector.utils;

import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.MessageDTO;
import io.netty.channel.ChannelHandlerContext;


/**
 * @description:
 * @author: plover
 * @date: 2023/7/21 15:51
 */
public final class SendMessageUtils {

    public static boolean sendError(ChannelHandlerContext ctx, String errorInfo) {
        return send(ctx, MessageDTO.builder().action(IMActionType.ERROR.code()).data(errorInfo).build());
    }

    public static boolean send(ChannelHandlerContext ctx, MessageDTO<Object> msg) {
        if (ctx == null || msg == null || !ctx.channel().isOpen()) {
            return false;
        }
        ctx.channel().writeAndFlush(msg);
        return true;
    }

    public static boolean send(ChannelHandlerContext ctx, String text, IMActionType actionType) {
        return send(ctx, MessageDTO.builder().action(actionType.code()).data(text).build());
    }

    public static void close(ChannelHandlerContext ctx) {
        ctx.close();
    }

}
