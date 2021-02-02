package com.cas.io.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:20 2020-11-25
 * @version: V1.0
 * @review:
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
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
                            // 加入一个netty提供IdleStateHandler

                            /**
                             * 说明
                             * 1. IdleStateHandler 是netty提供的处理空闲状态的处理器
                             * 2. Long readerIdleTime: 表示多长时间没有读， 就会发送一个心跳检测包检测是否连接
                             * 3. Long writerIdleTime: 表示多长时间没有写， 就会发送一个心跳检测包检测是否连接
                             * 4. Long allIdleTime: 表示多长时间没有读写， 就会发送一个心跳检测包检测是否连接
                             * 5. 当 IdleStateHandler 触发后， 就会传递给管道的下一个handler去处理
                             * 通过调用下一个handler的 userEventTiggered,
                             * 在该方法中去处理IdleStateHandler（读空闲， 写空闲， 读写空闲）
                             */
                            pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            // 加入一个对空闲检测进一步处理的handler（自定义）
                            pipeline.addLast(new MyServerhandler());
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
