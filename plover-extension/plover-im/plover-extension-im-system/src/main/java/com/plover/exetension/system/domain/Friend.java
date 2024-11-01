package com.plover.exetension.system.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 好友
 * @author: plover
 * @date: 2024/10/26 13:12
 */
@Getter
@Setter
@Builder
public class Friend {
    /**
     * 好友id
     */
    private String id;

    /**
     * 好友名称
     */
    private String name;

    /**
     * 好友描述
     */
    private String description;

    /**
     * 头像地址
     */
    private String avatar;
}
