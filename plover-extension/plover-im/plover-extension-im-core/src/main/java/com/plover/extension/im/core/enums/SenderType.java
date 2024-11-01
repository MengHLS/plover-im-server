package com.plover.extension.im.core.enums;

import lombok.Getter;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/27 20:00
 */
@Getter
public enum SenderType {


    PERSONAL(0, "私人");


    private final Integer code;

    private final String desc;

    SenderType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
