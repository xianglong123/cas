package com.cas.io.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:48 2020-11-25
 * @version: V1.0
 * @review:
 */
public class MyServer {

    public static void main(String[] args) throws Exception{
        // 创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {

            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 因为是基于http协议，使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 是以块方式写， 添加chunkedWrite处理器
                            pipeline.addLast(new ChunkedWriteHandler());

                            /**
                             * 说明
                             * 1. http数据在传输过程中是分段， httpObjectAggregator 就是可以将多个段聚合
                             * 2. 这就是为什么，当浏览器大量数据时，会发出多次http请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));

                            /**
                             * 说明
                             * 1. 对应websocker, 它的数据是以 帧（frame）形式传递
                             * 2. 可以看到webSocketFrame 下面有六个子类
                             * 3. 浏览器请求时 ws://localhost:7000/xxx 表示请求的url
                             * 4. WebSocketServerProtocolHandler 核心功能将 http协议升级为ws协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/log"));

                            // 自定义的handler， 处理业务逻辑
                            pipeline.addLast(new MyTestWebSocketFrameHandler());
                        }
                    });
            // 启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
