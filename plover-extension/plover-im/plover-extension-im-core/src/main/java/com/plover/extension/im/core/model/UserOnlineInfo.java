package com.plover.extension.im.core.model;

import lombok.*;

/**
 * @description: 用户在线信息
 * @author: plover
 * @date: 2024/11/7 20:17
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlineInfo {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 上线时间
     */
    private Long onlineTime;

    /**
     * 服务id
     */
    private String serviceId;

    /**
     * 设备类型
     */
    private int deviceType;
}
