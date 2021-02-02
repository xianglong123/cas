package com.cas.io.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 19:27 2020-11-18
 * @version: V1.0
 * @review:
 */
public class GroupChatServer {

    private int port; // 监听端口

    public GroupChatServer(int port) {
        this.port = port;
    }

    // 编写run方法， 处理客户端的请求
    public void run() throws Exception {

        // 创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 获取到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 向pipeline 加入解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            // 向pipeline 加入编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            // 加入自己的业务处理handler
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    });

            System.out.println(" netty 服务器启动");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            // 监听关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new GroupChatServer(7000).run();
    }


}
