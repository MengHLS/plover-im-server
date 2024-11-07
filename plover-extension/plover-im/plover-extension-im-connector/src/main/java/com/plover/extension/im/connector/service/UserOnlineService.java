package com.plover.extension.im.connector.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.plover.extension.im.core.enums.DeviceType;
import com.plover.extension.im.core.model.UserOnlineInfo;
import com.ruoyi.common.redis.configure.FastJson2JsonRedisSerializer;
import com.ruoyi.common.redis.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用户在线信息维护
 * @author: plover
 * @date: 2024/11/7 20:13
 */
@Service
public class UserOnlineService {

    @Resource
    private RedisService redisService;


    private static final String USER_ONLINE_KEY = "user_online";

    /**
     * 用户上线
     *
     * @param userOnlineInfo 用户在线信息。
     */
    public void userOnline(UserOnlineInfo userOnlineInfo) {
        redisService.setCacheMapValue(USER_ONLINE_KEY, userOnlineInfo.getUserId()+ "_" + userOnlineInfo.getDeviceType(), userOnlineInfo);
    }

    /**
     * 获取指定在线
     *
     * @param key 用户key
     * @return 用户在线信息
     */
    public UserOnlineInfo getUserOnlineInfo(String key) {
        JSONObject jsonObject = redisService.getCacheMapValue(USER_ONLINE_KEY, key);
        if (jsonObject == null) {
            return null;
        }
        return jsonObject.to(UserOnlineInfo.class);
    }

    /**
     * 获取用户多端在线信息
     *
     * @param userId 用户Id
     * @return 用户多端在线信息
     */
    public List<UserOnlineInfo> getAllUserOnlineInfo(String userId) {
        List<UserOnlineInfo> userOnlineInfoList = new ArrayList<>();
        UserOnlineInfo userOnlineInfo = getUserOnlineInfoByUserIdAndDeviceType(userId, DeviceType.MOBILE);
        if (userOnlineInfo != null) {
            userOnlineInfoList.add(userOnlineInfo);
        }
        userOnlineInfo = getUserOnlineInfoByUserIdAndDeviceType(userId, DeviceType.PC);
        if (userOnlineInfo != null) {
            userOnlineInfoList.add(userOnlineInfo);
        }
        userOnlineInfo = getUserOnlineInfoByUserIdAndDeviceType(userId, DeviceType.WEB);
        if (userOnlineInfo != null) {
            userOnlineInfoList.add(userOnlineInfo);
        }
        return userOnlineInfoList;
    }

    /**
     * 判断用户是否在线
     *
     * @param userId 用户id
     * @return 是否在线
     */
    public Boolean isUserOnline(String userId) {
        return CollUtil.isNotEmpty(getAllUserOnlineInfo(userId));
    }

    /**
     * 根据用户id和设备类型获取在线信息
     *
     * @param userId     用户id
     * @param deviceType 设备类型
     * @return 在线信息
     */
    public UserOnlineInfo getUserOnlineInfoByUserIdAndDeviceType(String userId, DeviceType deviceType) {
        return this.getUserOnlineInfo(getKey(userId, deviceType));
    }

    /**
     * 用户下线
     * @param key key
     */
    public void userOffline(String key) {
        redisService.deleteCacheMapValue(USER_ONLINE_KEY, key);
    }

    /**
     * 根据用户id和设备下线
     * @param userId 用户id
     * @param deviceType 设备
     */
    public void userOfflineByUserIdAndDeviceType(String userId, DeviceType deviceType) {
        userOffline(getKey(userId, deviceType));
    }


    /**
     * 获取key
     *
     * @param userId     用户id
     * @param deviceType 设备类型
     * @return 返回key
     */
    private String getKey(String userId, DeviceType deviceType) {
        return userId + "_" + deviceType.getCode();
    }
}
