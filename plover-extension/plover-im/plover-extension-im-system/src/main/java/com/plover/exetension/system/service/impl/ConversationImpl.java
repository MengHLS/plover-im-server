package com.plover.exetension.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plover.exetension.system.domain.Conversation;
import com.plover.exetension.system.service.ConversationService;
import com.plover.extension.im.core.constant.MongoConstant;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 会话服务类
 * @author: plover
 * @date: 2024/11/1 12:44
 */
@Service
public class ConversationImpl implements ConversationService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 分页获取指定时间内会话窗口
     *
     * @param page           分页参数
     * @param userId         用户id
     * @param startTimeStamp 开始时间戳
     * @param endTimeStamp   结束时间戳
     * @return 分页数据
     */
    @Override
    public Page<Conversation> findAllConversation(Page<Conversation> page, String userId, long startTimeStamp, long endTimeStamp) {
        //查询总数
        Criteria criteria = Criteria.where("timeStamp").gte(startTimeStamp).lte(endTimeStamp)
                .orOperator(Criteria.where("receiverId").is(userId),
                        Criteria.where("senderId").is(userId));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.addFields().addField("conversationId").withValue(
                        ConditionalOperators.when(Criteria.where("senderId").is(userId)).thenValueOf("receiverId")
                                .otherwiseValueOf("senderId"))
                        .build(),
                Aggregation.sort(Sort.Direction.DESC, "timeStamp"),
                Aggregation.group("conversationId")
                        .first("id").as("id")
                        .first("content").as("content")
                        .first("timeStamp").as("timeStamp")
                        .first("senderId").as("senderId")
                        .first("senderName").as("senderName")
                        .first("senderType").as("senderType")
                        .first("receiverId").as("receiverId")
                        .first("receiverType").as("receiverType")
                        .first("receiverName").as("receiverName")
                        .first("conversationId").as("conversationId"),
                Aggregation.sort(Sort.Direction.DESC, "timeStamp")
        );
        long count = mongoTemplate.aggregateStream(aggregation, MongoConstant.OFFLINE_MESSAGE_STORAGE_CONNECTION_NAME, Conversation.class).count();
        if (count > 0) {
            List<Conversation> conversations = mongoTemplate.aggregate(aggregation, MongoConstant.OFFLINE_MESSAGE_STORAGE_CONNECTION_NAME, Conversation.class).getMappedResults();
            conversations.forEach(conversation -> {
                if (conversation.getSenderId().equals(userId)) {
                    conversation.setConversationType(conversation.getReceiverType());
                    conversation.setName(conversation.getReceiverName());
                } else if (conversation.getReceiverId().equals(userId)) {
                    conversation.setConversationType(conversation.getSenderType());
                    conversation.setName(conversation.getSenderName());
                }
            });
            page.setRecords(conversations);
        }
        page.setTotal(count);
        //分页查询对应时间段内所有对话的最新一条消息。
        return page;
    }
}
