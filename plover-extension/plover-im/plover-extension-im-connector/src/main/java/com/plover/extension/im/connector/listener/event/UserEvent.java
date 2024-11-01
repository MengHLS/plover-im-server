package com.plover.extension.im.connector.listener.event;

import io.netty.channel.ChannelHandlerContext;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 用户事件
 * @author: plover
 * @date: 2024/8/22 20:18
 */
@Data
@Builder
public class UserEvent {

    private ChannelHandlerContext ctx;

    private String userId;

    private Event event;

    public static UserEvent buildOnlineEvent(String userId, ChannelHandlerContext ctx) {
        return UserEvent.builder().ctx(ctx).userId(userId).event(Event.ONLINE).build();
    }

    public static UserEvent buildOfflineEvent(String userId, ChannelHandlerContext ctx) {
        return UserEvent.builder().ctx(ctx).userId(userId).event(Event.OFFLINE).build();
    }

    public enum Event {

        /**
         * 上线
         */
        ONLINE,

        /**
         * 下线
         */
        OFFLINE;

    }
}
