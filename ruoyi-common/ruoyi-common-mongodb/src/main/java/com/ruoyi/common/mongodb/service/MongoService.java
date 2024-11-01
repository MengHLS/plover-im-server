package com.ruoyi.common.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/30 10:20
 */
@Component
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存消息
     * @param object 消息实体类
     */
    public void insert(Object object, String collectionName){
        mongoTemplate.insert(object,collectionName);
    }
}
