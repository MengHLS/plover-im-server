package com.plover.exetension.im.server.service;

import com.plover.extension.im.connector.remote.netty.UserChannelCtxMap;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import com.plover.extension.im.core.model.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/31 22:20
 */
@Service
public class ForwardMessageService {

    /**
     * 转发消息
     * @param messageDTO 消息类
     * @param personId 用户Id
     */
    public void forwardMessageToPerson(MessageDTO<Object> messageDTO, String personId) {
        Objects.requireNonNull(UserChannelCtxMap.getChannelCtx(personId)).forEach(c-> SendMessageUtils.send(c, messageDTO));
    }
}
