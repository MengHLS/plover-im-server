package com.plover.exetension.system.controller;

import cn.hutool.core.date.DateUtil;
import com.plover.exetension.system.domain.Conversation;
import com.plover.exetension.system.domain.Friend;
import com.plover.exetension.system.domain.Group;
import com.plover.exetension.system.domain.Message;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 会话controller
 * @author: plover
 * @date: 2024/8/23 18:35
 */
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @GetMapping("/list")
    public AjaxResult listConversation() {
        List<Conversation> conversations = new ArrayList<Conversation>();

        Message message = Message.builder()
                .id("947")
                .type(0)
                .content("今天天气不错")
                .sender("168")
                .senderName("陶喆")
                .senderAvatar("https://chatterbox.gumingchen.icu/resource/avatar/40b9a60c-c85e-4d28-ab7e-98e856299674.png")
                .createdAt(DateUtil.now())
                .build();
        Conversation conversation = Conversation.builder()
                .roomId("1")
                .userId("170")
                .group(Group.builder().id("1").name("测试")
                        .avatar("https://chatterbox.gumingchen.icu/resource/static/logo-white-bg.png")
                        .build())
                .message(message)
                .build();

        conversations.add(conversation);

        conversation = Conversation.builder()
                .userId("168")
                .friend(Friend.builder().id("168").name("陶喆")
                        .avatar("https://chatterbox.gumingchen.icu/resource/static/logo-white-bg.png")
                        .build())
                .message(message)
                .build();
        conversations.add(conversation);
        return AjaxResult.success(conversations);
    }
}
