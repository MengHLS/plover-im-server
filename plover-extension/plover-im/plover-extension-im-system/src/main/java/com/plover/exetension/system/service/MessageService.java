package com.plover.exetension.system.service;

import com.plover.extension.im.core.model.BaseMessage;

import java.util.List;

/**
 * @description: 消息服务
 * @author: plover
 * @date: 2024/11/3 1:19
 */
public interface MessageService {

    /**
     * 根据最后一条消息的id获取之前的消息列表
     * @param senderId 发送者Id
     * @param receiverId 接受者id
     * @param lastId 最后一条消息的id
     * @param limit 限制数量
     * @return 基础消息list
     */
    List<BaseMessage> getPrivateMessagesList(String senderId, String receiverId, String lastId, int limit);
}
