package com.plover.exetension.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plover.exetension.system.domain.Conversation;

/**
 * @description: 会话服务
 * @author: plover
 * @date: 2024/11/1 12:43
 */
public interface ConversationService {

    /**
     * 分页获取指定时间内会话窗口
     * @param page 分页参数
     * @param userId 用户id
     * @param startTimeStamp 开始时间戳
     * @param endTimeStamp 结束时间戳
     * @return 分页数据
     */
    Page<Conversation> findAllConversation(Page<Conversation> page, String userId, long startTimeStamp, long endTimeStamp);
}
