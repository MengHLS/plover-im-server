package com.plover.extension.im.connector.remote.netty;

import com.plover.extension.im.connector.processor.MessageProcessor;
import com.plover.extension.im.connector.processor.ProcessorFactory;
import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.MessageDTO;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 长连接下
 * @author: plover
 * @date: 2024/8/8 2:41
 */
@Slf4j
public class IMChannelHandler<T> extends SimpleChannelInboundHandler<MessageDTO<T>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageDTO<T> messageDTO) {
        if (messageDTO == null) {
            return;
        }
        IMActionType imCmdType = IMActionType.fromCode(messageDTO.getAction());
        if (imCmdType == null) {
            return;
        }
        // 创建处理器进行处理
        MessageProcessor processor = ProcessorFactory.createProcessor(imCmdType);
        if (processor == null) {
            return;
        }
        processor.process(ctx, messageDTO.getData());
    }

    /**
     * 监控浏览器上线
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("{}连接", ctx.channel().id().asLongText());
        SendMessageUtils.send(ctx, MessageDTO.builder().action(IMActionType.LOGIN.getCode()).data("登陆成功").build());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        Long userId = this.getUserIdFromChannelContext(ctx);
//        ChannelHandlerContext context = UserChannelCtxMap.getChannelCtx(userId);
//        // 判断一下，避免异地登录导致的误删
//        if (context != null && ctx.channel().id().equals(context.channel().id())) {
//            SpringContextHolder.sendEvent(UserEvent.buildOfflineEvent(userId, ctx));
//            // 移除channel
//            UserChannelCtxMap.removeChannelCtx(userId);
//            log.info("断开连接,userId:{}", userId);
//        }

        log.info("{}断开连接", ctx.channel().id().asLongText());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //处理客户端异常断开

        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        //……
        if(channel.isActive()){
            ctx.close();
        }

    }
}
