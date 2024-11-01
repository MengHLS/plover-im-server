package com.plover.exetension.system.controller;

import cn.hutool.core.date.DateUtil;
import com.plover.exetension.system.domain.Message;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 消息相关方法
 * @author: plover
 * @date: 2024/10/26 17:20
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/list")
    public AjaxResult listMessage(@RequestParam("userId")String userId,
                                       @RequestParam(value = "lastId",required = false)String lastId,
                                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "40")int pageSize) {

        List<Message> messages = new ArrayList<>();
        Message message = Message.builder()
                .id("947")
                .type(0)
                .content("今天天气不错")
                .sender("168")
                .senderName("陶喆")
                .senderAvatar("https://chatterbox.gumingchen.icu/resource/avatar/40b9a60c-c85e-4d28-ab7e-98e856299674.png")
                .createdAt(DateUtil.now())
                .build();
        messages.add(message);
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.setCode(0);
        dataInfo.setRows(messages);
        dataInfo.setTotal(messages.size());
        return AjaxResult.success(dataInfo);
    }
}
