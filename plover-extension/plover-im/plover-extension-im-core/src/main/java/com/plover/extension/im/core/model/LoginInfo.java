package com.plover.extension.im.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 登录信息实体类
 * @author: plover
 * @date: 2024/8/21 18:01
 */
@Getter
@Setter
public class LoginInfo {

    /**
     * token
     */
    private String token;

    /**
     * 设备类型
     */
    private int deviceType;

    /**
     * 系统类型
     */
    private int osType;
}
