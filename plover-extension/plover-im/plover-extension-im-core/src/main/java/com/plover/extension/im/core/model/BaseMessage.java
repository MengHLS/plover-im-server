package com.plover.extension.im.core.model;

import lombok.*;

/**
 * @description: 基础消息bean
 * @author: plover
 * @date: 2024/10/26 21:58
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseMessage {

    /**
     * 消息id
     */
    private String id;

    /**
     * 消息类型
     */
    private int type;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送者id
     */
    private String senderId;

    /**
     * 发送者名称
     */
    private String senderName;

    /**
     * 发送者类型
     */
    private int senderType;

    /**
     * 接收者id
     */
    private String receiverId;

    /**
     * 接受者名称
     */
    private String receiverName;

    /**
     * 接受者类型
     */
    private int receiverType;

    /**
     * 发送时间
     */
    private String createTime;

    /**
     * 服务器接收到消息的时间戳
     */
    private long timeStamp;
}
