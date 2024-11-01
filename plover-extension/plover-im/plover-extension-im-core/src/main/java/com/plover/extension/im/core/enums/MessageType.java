package com.plover.extension.im.core.enums;

import lombok.Getter;

/**
 * @description: 消息类型
 * @author: plover
 * @date: 2024/10/26 12:58
 */
@Getter
public enum MessageType {

    TEXT(0, "文本消息");


    private final Integer code;

    private final String desc;

    MessageType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
