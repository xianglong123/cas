package com.cas.io.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:20 2020-11-13
 * @version: V1.0
 * @review:
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // 创建BossGroup 和 workGroup
        // 说明
        // 1. 创建两个线程组 bossGroup 和 workerGroup
        // 2. bossGroup 只是处理连接请求，真正的和客户端业务出炉会交给workGroup
        // 3. 两个都是无限循环
        // 4. boosGroup 和 workGroup 含有的子线程（NioEventLoop）的个数
        // 默认实际 cpu * 2  sout(NettyRuntime.availableProcessors())
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用链式变成进行设置
            bootstrap.group(bossGroup, workGroup) // 设置两个线程组
                    .channel(NioServerSocketChannel.class) // 使用 NioServerSocketChannel 作为服务器的通信实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                    .handler(null) // handler 给bossGroup 使用， childHandler 给 workGroup 使用
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道测试对象
                        // 给pipline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    }); // 给我们的workerGroup 的 EventLoop 对应的管道设置处理器

            System.out.println("..... 服务器 is ready");

            // 绑定一个端口并且同步，生成一个channelFuture对象
            ChannelFuture cf = bootstrap.bind(6668).sync();

            // 给cf 注册监听器， 监听我们关心的事件
            cf.addListener((ChannelFutureListener) future -> {
                if (cf.isSuccess()) {
                    System.out.println("监听端口 6668 成功");
                } else {
                    System.out.println("监听端口 6668 失败");
                }
            });


            // 对关闭通道进行监听，不是立马关闭，而是监听
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
