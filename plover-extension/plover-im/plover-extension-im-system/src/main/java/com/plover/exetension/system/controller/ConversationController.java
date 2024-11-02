package com.plover.exetension.system.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plover.exetension.system.domain.Conversation;
import com.plover.exetension.system.service.ConversationService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会话controller
 * @author: plover
 * @date: 2024/8/23 18:35
 */
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Resource
    private ConversationService conversationService;


    @GetMapping("/list")
    public AjaxResult listConversation() {
        Page<Conversation> page = new Page<>();
        DateTime dateTime = DateUtil.date();
        return AjaxResult.success(conversationService.findAllConversation(page, SecurityUtils.getUserId().toString(),
                dateTime.offsetNew(DateField.HOUR,-7*24).getTime(), dateTime.toTimestamp().getTime()));
    }
}
