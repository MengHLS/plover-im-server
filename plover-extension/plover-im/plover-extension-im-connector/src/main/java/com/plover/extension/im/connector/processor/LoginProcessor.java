package com.plover.extension.im.connector.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.plover.extension.im.connector.configure.IMServerConfigProperties;
import com.plover.extension.im.connector.listener.event.UserEvent;
import com.plover.extension.im.connector.remote.netty.UserChannelCtxMap;
import com.plover.extension.im.connector.service.UserOnlineService;
import com.plover.extension.im.core.constant.ConnectorConst;
import com.plover.extension.im.core.enums.IMActionType;
import com.plover.extension.im.core.model.LoginInfo;
import com.plover.extension.im.core.model.MessageDTO;
import com.plover.extension.im.connector.utils.SendMessageUtils;
import com.plover.extension.im.core.model.UserOnlineInfo;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.SpringContextHolder;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import io.jsonwebtoken.Claims;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 处理登录逻辑
 * @author: plover
 * @date: 2024/8/21 17:54
 */
@Slf4j
@Component
@Processor(type = IMActionType.LOGIN)
public class LoginProcessor implements MessageProcessor {

    @Autowired
    private RedisService redisService;

    @Resource
    private UserOnlineService userOnlineService;
    @Resource
    private IMServerConfigProperties imServerConfigProperties;

    @Override
    public void process(ChannelHandlerContext ctx, Object data) {
        //todo 处理登录逻辑


        LoginInfo loginInfo = JSONUtil.toBean(data.toString(), LoginInfo.class);
        if (StringUtils.isEmpty(loginInfo.getToken())) {
            SendMessageUtils.sendError(ctx, "令牌不能为空");
            return;
        }
        Claims claims = JwtUtils.parseToken(loginInfo.getToken());
        if (claims == null) {
            SendMessageUtils.sendError(ctx,"令牌已过期或验证不正确！");
            return;
        }
        String userKey = JwtUtils.getUserKey(claims);
        boolean isLogin = redisService.hasKey(getTokenKey(userKey));
        if (!isLogin) {
            SendMessageUtils.sendError(ctx,"登录状态已过期");
            return;
        }
        String userId = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(username)) {
            SendMessageUtils.sendError(ctx,"令牌验证失败");
            return;
        }
        ChannelHandlerContext context = UserChannelCtxMap.getChannelCtx(userId, loginInfo.getDeviceType());
        if (context != null) {

            // 不允许多地登录,强制下线
            SendMessageUtils.send(context, MessageDTO.builder().action(IMActionType.FORCE_LOGOUT.code()).data("强制下线").build());
            userOnlineService.userOffline(userId+ "_" + loginInfo.getDeviceType());

            SendMessageUtils.close(context);
        }

        // 绑定用户和channel
        UserChannelCtxMap.addChannelCtx(userId, ctx, loginInfo.getDeviceType());
        // 设置用户id属性
        ctx.channel().attr(AttributeKey.valueOf(ConnectorConst.USER_ID)).set(userId);
        // 心跳次数
        ctx.channel().attr(AttributeKey.valueOf(ConnectorConst.HEARTBEAT_TIMES)).set(0L);
        SpringContextHolder.sendEvent(UserEvent.buildOnlineEvent(userId, ctx));
        log.info("用户{}已登录", JwtUtils.getUserName(loginInfo.getToken()));

        UserOnlineInfo userOnlineInfo = UserOnlineInfo.builder().userId(userId)
                .onlineTime(DateUtil.current())
                .userName(username)
                .serviceId(imServerConfigProperties.getServiceId())
                .deviceType(loginInfo.getDeviceType())
                .build();

        userOnlineService.userOnline(userOnlineInfo);

        SendMessageUtils.send(ctx, MessageDTO.builder().action(IMActionType.LOGIN_SUCCESS.code()).data("登陆成功").build());

    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }
}


