package com.plover.exetension.system.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 消息类
 * @author: plover
 * @date: 2024/8/23 20:17
 */
@Getter
@Setter
@Builder
public class Message {


    /**
     * 消息id
     */
    private String id;

    /**
     * 信息类型
     */
    private int type;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送者id
     */
    private String sender;

    /**
     * 发送者名称
     */
    private String senderName;

    /**
     * 发送者头像
     */
    private String senderAvatar;

    /**
     * 接受者
     */
    private String receiver;

    /**
     * 发送类型
     */
    private String senderType;

    /**
     * 创建时间
     */
    private String createdAt;
}
