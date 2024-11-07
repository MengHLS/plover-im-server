package com.plover.extension.im.cluster.listener;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.plover.extension.im.connector.remote.netty.UserChannelCtxMap;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.BaseMessage;
import com.plover.extension.im.core.model.MessageDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * @description:
 * @author: plover
 * @date: 2024/11/7 12:41
 */
@Slf4j
@Component
public class ForwardMessageListener implements MessageListener {


    /**
     * Callback for processing received objects through Redis.
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info( message.toString());
        MessageDTO messageDTO = JSONUtil.toBean(message.toString(), MessageDTO.class);
        if (messageDTO.getAction().equals(IMActionType.PRIVATE_MESSAGE.getCode())){
            JSONObject jsonObject = (JSONObject) messageDTO.getData();
            BaseMessage baseMessage = jsonObject.toBean(BaseMessage.class);
            messageDTO.setData(baseMessage);
            Objects.requireNonNull(UserChannelCtxMap.getChannelCtx(baseMessage.getReceiverId())).forEach(c-> SendMessageUtils.send(c, messageDTO));
        }
    }
}
