package com.plover.extension.im.connector.remote.netty.ws;

import com.plover.extension.im.connector.configure.IMServerConfigProperties;
import com.plover.extension.im.connector.remote.netty.AbstractRemoteServer;
import com.plover.extension.im.connector.remote.netty.factory.NettyEventLoopFactory;
import com.plover.extension.im.connector.remote.netty.ws.coder.MessageProtocolDecoder;
import com.plover.extension.im.connector.remote.netty.ws.coder.MessageProtocolEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: websocket实现
 * @author: plover
 * @date: 2024/8/8 1:08
 */
@Slf4j
@Component
public class WebSocketServer extends AbstractRemoteServer {

    public WebSocketServer() {
        super();
    }

    @Override
    public void start() {
        bootstrap.group(bossGroup, workGroup)
                //设置服务端NIO通信类型
                .channel(NettyEventLoopFactory.serverSocketChannelClass())
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, false)
                // 表示连接保活，相当于心跳机制，默认为7200s
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<>() {

                    // 添加处理的Handler，通常包括消息编解码、业务处理，也可以是日志、权限、过滤等
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        // 获取职责链
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("http-codec", new HttpServerCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(65535));
                        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                        pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                        pipeline.addLast("encode", new MessageProtocolEncoder());
                        pipeline.addLast("decode", new MessageProtocolDecoder());
                        addPipeline(pipeline);
                    }
                });
        try {
            Integer port = super.port();
            // 绑定端口，启动select线程，轮询监听channel事件，监听到事件之后就会交给从线程池处理
            Channel channel = bootstrap.bind(port).sync().channel();
            // 就绪标志
            this.ready = true;
            log.info("websocket server 初始化完成,端口：{}", port);
        } catch (InterruptedException e) {
            log.error("websocket server 初始化异常", e);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    protected IMServerConfigProperties.TcpNode nodeInfo() {
        return imServerConfigProperties.getWs();
    }
}
