package com.plover.exetension.system.controller;

import cn.hutool.core.date.DateUtil;
import com.plover.exetension.system.domain.Message;
import com.plover.exetension.system.service.MessageService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.utils.SecurityUtils;
import jakarta.annotation.Resource;
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

    @Resource
    private MessageService messageService;

    @GetMapping("/list/user")
    public AjaxResult listMessage(@RequestParam("userId")String userId,
                                       @RequestParam(value = "lastId",required = false)String lastId,
                                       @RequestParam(value = "pageSize",defaultValue = "40")int pageSize) {

        return AjaxResult.success(messageService.getPrivateMessagesList(userId, SecurityUtils.getUserId().toString(), lastId, pageSize));
    }
}
