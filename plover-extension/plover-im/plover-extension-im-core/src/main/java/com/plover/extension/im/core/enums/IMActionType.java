package com.plover.extension.im.core.enums;

import lombok.Getter;

/**
 * 消息的动作类型
 */
@Getter
public enum IMActionType {

    ERROR(-1, "信息异常"),
    HEART_BEAT(0, "心跳"),
    LOGIN(1, "登陆"),
    LOGIN_SUCCESS(2, "登陆成功"),
    FORCE_LOGOUT(3, "强制下线"),
    PRIVATE_MESSAGE(4, "私聊消息"),
    GROUP_MESSAGE(5, "群发消息"),
    RECEIPT_MESSAGE(6, "回执消息");

    private final Integer code;

    private final String desc;

    IMActionType(Integer index, String desc) {
        this.code = index;
        this.desc = desc;
    }

    public static IMActionType fromCode(Integer code) {
        for (IMActionType typeEnum : values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }

    public String description() {
        return desc;
    }

    public Integer code() {
        return this.code;
    }
}
