package com.plover.extension.im.connector.remote.netty;

import cn.hutool.core.util.StrUtil;
import com.plover.extension.im.core.enums.DeviceType;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 连接用户维护池
 * @author: plover
 * @date: 2024/8/22 19:21
 */
public class UserChannelCtxMap {

    /*
     * 维护移动端userId和ctx的关联关系，格式:Map<userId,ctx>
     */
    private static final Map<String, ChannelHandlerContext> mobileChannelMap = new ConcurrentHashMap<>();

    /*
     * 维护pc端userId和ctx的关联关系，格式:Map<userId,ctx>
     */
    private static final Map<String, ChannelHandlerContext> pcChannelMap = new ConcurrentHashMap<>();


    public static void addChannelCtx(String userId, ChannelHandlerContext ctx, int deviceType) {
        if (deviceType == DeviceType.MOBILE.getCode()){
            mobileChannelMap.put(userId, ctx);
        } else if (deviceType == DeviceType.PC.getCode()){
            pcChannelMap.put(userId, ctx);
        }
    }

    public static void removeChannelCtx(String userId, int deviceType) {
        if (userId != null && deviceType == DeviceType.MOBILE.getCode()) {
            mobileChannelMap.remove(userId);
        } else if (userId != null && deviceType == DeviceType.PC.getCode()) {
            pcChannelMap.remove(userId);
        }
    }

    public static ChannelHandlerContext getChannelCtx(String userId, int deviceType) {
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        if (deviceType == DeviceType.MOBILE.getCode()) {
            return mobileChannelMap.get(userId);
        }
        return pcChannelMap.get(userId);
    }

    public static List<ChannelHandlerContext> getChannelCtx(String userId) {
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        List<ChannelHandlerContext> list = new ArrayList<>();
        list.add(mobileChannelMap.get(userId));
        list.add(pcChannelMap.get(userId));
        return list;
    }
}
