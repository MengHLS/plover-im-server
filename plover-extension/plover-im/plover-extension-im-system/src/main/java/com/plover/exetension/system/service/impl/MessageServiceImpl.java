package com.plover.exetension.system.service.impl;

/**
 * @description: 消息服务
 * @author: plover
 * @date: 2024/11/3 1:20
 */

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.plover.exetension.system.service.MessageService;
import com.plover.extension.im.core.constant.MongoConstant;
import com.plover.extension.im.core.model.BaseMessage;
import com.ruoyi.common.core.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 根据最后一条消息的id获取之前的消息列表
     *
     * @param senderId   发送者Id
     * @param receiverId 接受者id
     * @param lastId     最后一条消息的id
     * @param limit      限制数量
     * @return 基础消息list
     */
    @Override
    public List<BaseMessage> getPrivateMessagesList(String senderId, String receiverId, String lastId, int limit) {
        long time = DateUtil.current();
        if (!StringUtils.isEmpty(lastId)){
            BaseMessage lastMessage = mongoTemplate.findOne(Query.query(Criteria.where("id").is(lastId)),BaseMessage.class, MongoConstant.MESSAGE_STORAGE_CONNECTION_NAME);
            if (lastMessage != null){
                time = lastMessage.getTimeStamp();
            }
        }

        Criteria criteria = Criteria.where("timeStamp").lt(time)
                .orOperator(
                        Criteria.where("senderId").is(senderId).and("receiverId").is(receiverId),
                        Criteria.where("senderId").is(receiverId).and("receiverId").is(senderId)
                );
        Query query = Query.query(criteria).with(Sort.by(Sort.Order.desc("timeStamp"))).limit(limit);
        List<BaseMessage> baseMessages = mongoTemplate.find(query, BaseMessage.class, MongoConstant.MESSAGE_STORAGE_CONNECTION_NAME);

        return baseMessages;
    }
}
