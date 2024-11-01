package com.plover.extension.im.core.model;

import com.plover.extension.im.core.enums.IMActionType;
import lombok.*;

/**
 * @description: 消息包装类
 * @author: plover
 * @date: 2024/8/8 2:01
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO<T> {

    /**
     * 命令
     */
    private Integer action;

    /**
     * 推送消息体
     */
    private T data;

    public static MessageDTO<String> create(IMActionType actionType) {
        MessageDTO<String> sendInfo = new MessageDTO<>();
        sendInfo.setAction(actionType.code());
        return sendInfo;
    }

    public static MessageDTO<String> create(IMActionType actionType, String msg) {
        MessageDTO<String> sendInfo = create(actionType);
        sendInfo.setData(msg);
        return sendInfo;
    }


}
