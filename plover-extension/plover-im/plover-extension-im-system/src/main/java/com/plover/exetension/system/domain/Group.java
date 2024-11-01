package com.plover.exetension.system.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/23 18:52
 */
@Getter
@Setter
@Builder
public class Group {


    /**
     * 群组id
     */
    private String id;

    /**
     * 群组名称
     */
    private String name;

    /**
     * 群描述
     */
    private String description;

    /**
     * 头像地址
     */
    private String avatar;
}
