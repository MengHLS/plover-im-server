package com.plover.exetension.im.server.service;

import com.plover.extension.im.cluster.configure.IMServerClusterConfigProperties;
import com.plover.extension.im.connector.configure.IMServerConfigProperties;
import com.plover.extension.im.connector.remote.netty.UserChannelCtxMap;
import com.plover.extension.im.connector.service.UserOnlineService;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import com.plover.extension.im.core.model.MessageDTO;
import com.plover.extension.im.core.model.UserOnlineInfo;
import com.ruoyi.common.redis.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/31 22:20
 */
@Service
public class ForwardMessageService {

    private final RedisService redisService;

    public ForwardMessageService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Resource
    private IMServerClusterConfigProperties imServerClusterConfigProperties;
    /**
     * 转发消息
     * @param messageDTO 消息类
     * @param personId 用户Id
     */
    public void forwardMessageToPerson(MessageDTO<Object> messageDTO, String personId) {
        Objects.requireNonNull(UserChannelCtxMap.getChannelCtx(personId)).forEach(c-> SendMessageUtils.send(c, messageDTO));
        redisService.convertAndSend(imServerClusterConfigProperties.getForwardChannelName(), messageDTO);
    }
}
