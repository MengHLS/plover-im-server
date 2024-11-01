package com.plover.extension.im.connector.listener.event;

import com.plover.extension.im.core.enums.NetProtocolEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 节点注册事件
 * @author: plover
 * @date: 2024/8/8 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NodeRegisterEvent {

    /**
     * 协议类型
     */
    private NetProtocolEnum netProtocolEnum;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 注册时间
     */
    private Long registerTime;
}
