package com.plover.extension.im.connector.remote;

/**
 * @description: 长连接服务接口，
 * @author: plover
 * @date: 2024/8/8 0:45
 */
public interface IMServer {

    /**
     * 是否激活监听器
     * @return 默认返回false
     */
    default boolean enable(){
        return false;
    }

    /**
     * 服务启动时需要执行的逻辑
     */
    void start();

    /**
     * 服务停止时需要执行的逻辑
     */
    void stop();
}
