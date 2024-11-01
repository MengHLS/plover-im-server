package com.plover.extension.im.core.enums;

import lombok.Getter;

/**
 * @description: 设备类型
 * @author: plover
 * @date: 2024/8/21 18:07
 */
@Getter
public enum DeviceType {

    MOBILE(1,"mobile"),
    PC(2,"pc"),
    WEB(3,"web");

    private final int code;
    private final String desc;

    DeviceType(Integer index, String desc) {
        this.code = index;
        this.desc = desc;
    }
}
