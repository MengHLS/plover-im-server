package com.plover.extension.im.connector.remote;

import com.plover.extension.im.core.constant.RedisKey;
import com.ruoyi.common.redis.service.RedisService;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IMServerGroup implements CommandLineRunner {

    public static volatile long serverId = -1;

    @Autowired
    private RedisService redisService;

    @Autowired
    public List<IMServer> imServers;

    @Override
    public void run(String... args) throws Exception {
        // 初始化SERVER_ID
        serverId = redisService.increment(RedisKey.IM_MAX_SERVER_ID);
        for (IMServer imServer : imServers) {
            if (imServer.enable()) {
                imServer.start();
            }
        }
    }

    @PreDestroy
    public void destroy() {
        // 停止服务
        for (IMServer imServer : imServers) {
            imServer.stop();
        }
    }
}
