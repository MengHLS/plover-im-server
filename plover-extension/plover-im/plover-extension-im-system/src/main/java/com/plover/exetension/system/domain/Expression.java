package com.plover.exetension.system.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/26 19:06
 */
@Getter
@Setter
@Builder
public class Expression {

    /**
     * id
     */
    private String id;

    /**
     * 链接
     */
    private String url;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private int type;
}
