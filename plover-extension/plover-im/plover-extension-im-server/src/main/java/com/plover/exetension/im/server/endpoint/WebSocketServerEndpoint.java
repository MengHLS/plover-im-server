package com.plover.exetension.im.server.endpoint;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.plover.extension.im.connector.configure.IMServerConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.net.InetAddress;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/27 12:14
 */
@Configuration
public class WebSocketServerEndpoint {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    protected IMServerConfigProperties imServerConfigProperties;


    @PostConstruct
    public void start() {
        if (imServerConfigProperties.getWs().getEnable()){
            // 注册到Nacos里
            registerNamingService(applicationName+"-ws", imServerConfigProperties.getWs().getPort());
        }
    }

    /**
     * 注册到 nacos 服务中
     *
     * @param nettyName netty服务名称
     * @param nettyPort netty服务端口
     */
    private void registerNamingService(String nettyName, int nettyPort) {
        try {
            NamingService namingService = NamingFactory.createNamingService(nacosDiscoveryProperties.getNacosProperties());
            InetAddress address = InetAddress.getLocalHost();
            namingService.registerInstance(nettyName, address.getHostAddress(), nettyPort);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
