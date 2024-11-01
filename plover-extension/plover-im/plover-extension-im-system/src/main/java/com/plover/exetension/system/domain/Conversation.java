package com.plover.exetension.system.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/23 18:42
 */
@Getter
@Setter
@Builder
public class Conversation {


    /**
     * 群组id
     */
    private String roomId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 群组信息
     */
    private Group group;

    /**
     * 好友信息
     */
    private Friend friend;

    /**
     * 消息
     */
    private Message message;
}
