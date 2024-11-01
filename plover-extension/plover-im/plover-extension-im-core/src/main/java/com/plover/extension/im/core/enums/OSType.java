package com.plover.extension.im.core.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/21 18:03
 */
@Getter
public enum OSType {

    Android(1,"Android"),
    iOS(2,"iOS"),
    Windows(3,"Windows"),
    Mac(4,"Mac"),
    Harmony(5,"Harmony");


    private final Integer code;

    private final String desc;
    private

    OSType(Integer index, String desc) {
        this.code = index;
        this.desc = desc;
    }
}
