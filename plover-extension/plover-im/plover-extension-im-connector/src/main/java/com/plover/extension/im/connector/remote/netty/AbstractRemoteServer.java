package com.plover.extension.im.connector.remote.netty;

import cn.hutool.core.util.NumberUtil;
import com.plover.extension.im.connector.configure.IMServerConfigProperties;
import com.plover.extension.im.connector.remote.IMServer;
import com.plover.extension.im.connector.remote.netty.factory.NettyEventLoopFactory;
import com.plover.extension.im.core.constant.AppConst;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/8 0:53
 */
public abstract class AbstractRemoteServer implements IMServer {

    /**
     * netty启动
     */
    protected ServerBootstrap bootstrap;

    /**
     * 接收客户端请求的线程池
     */
    protected EventLoopGroup bossGroup;

    /**
     * 处理客户端请求的线程池
     */
    protected EventLoopGroup workGroup;

    protected volatile boolean ready = false;

    @Autowired
    protected IMServerConfigProperties imServerConfigProperties;


    protected abstract IMServerConfigProperties.TcpNode nodeInfo();

    public AbstractRemoteServer() {
        bootstrap = new ServerBootstrap();
        bossGroup = NettyEventLoopFactory.eventLoopGroup(1);
        workGroup = NettyEventLoopFactory.eventLoopGroup(Math.min(Runtime.getRuntime().availableProcessors() + 1, 32));
    }

    /**
     * 获的一个端口号
     *
     * @return 返回一个端口号
     */
    protected Integer port() {
        IMServerConfigProperties.TcpNode tcpNode = nodeInfo();
        Integer port = tcpNode.getPort();
        if (port == null || port < 1) {
            port = NumberUtil.generateRandomNumber(20000, 30000, 1)[0];
            tcpNode.setPort(port);
        }
        return port;
    }

    protected void addPipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new IdleStateHandler(0, 0, AppConst.ONLINE_TIMEOUT_SECOND, TimeUnit.SECONDS));
        pipeline.addLast(new IMChannelHandler<>());
        pipeline.addLast(new TextChannelHandler());
    }

    @Override
    public boolean enable() {
        IMServerConfigProperties.TcpNode tcpNode = nodeInfo();
        return tcpNode != null && tcpNode.getEnable();
    }
}
