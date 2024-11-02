package com.plover.exetension.system.domain;

import com.plover.extension.im.core.model.BaseMessage;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/23 18:42
 */
@Getter
@Setter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Conversation extends BaseMessage {


    /**
     * 会话Id
     */
    private String conversationId;

    /**
     * 会话名称
     */
    private String name;

    /**
     * 会话类型
     */
    private int conversationType;
}
