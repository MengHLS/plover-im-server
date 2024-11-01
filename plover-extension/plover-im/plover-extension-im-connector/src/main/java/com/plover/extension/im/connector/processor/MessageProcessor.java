package com.plover.extension.im.connector.processor;

import cn.hutool.core.util.StrUtil;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description: 消息处理接口
 * @author: plover
 * @date: 2024/8/8 2:54
 */
public interface MessageProcessor {

    void process(ChannelHandlerContext ctx, Object data);

    default Long parseUserId(ChannelHandlerContext ctx, String token) {
        if (StrUtil.isEmpty(token)) {
            SendMessageUtils.sendError(ctx, "未登录");
            throw new IllegalArgumentException("未登录");
        }

//        try {
//            // 验证 token
//            JwtUtils.checkSign(token, AppConst.ACCESS_TOKEN_SECRET);
//        } catch (JWTVerificationException e) {
//            SendMessageUtils.sendError(ctx, "token已失效");
//            throw new IllegalArgumentException("token已失效");
//        }

        return 1L;

//        try {
//            return SecurityUtils.getUserId();
//        } catch (Exception e) {
//            SendMessageUtils.sendError(ctx, "token有误");
//            throw new IllegalArgumentException("token有误");
//        }
    }
}
