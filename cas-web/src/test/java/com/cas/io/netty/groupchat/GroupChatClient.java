package com.cas.io.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:01 2020-11-25
 * @version: V1.0
 * @review:
 */
public class GroupChatClient {

    // 属性
    private final String host;
    private final int port;


    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void run() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            // 得到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 加入相关的handler
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // 加入自定义的handler
                            pipeline.addLast(new GroupChatClienthandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            // 得到channel
            Channel channel = channelFuture.channel();
            System.out.println("-----" + channel.localAddress() + "-----");
            // 客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                // 通过channel 发送消息给服务器端
                channel.writeAndFlush(msg + "\r\t");
            }
        } finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new GroupChatClient("127.0.0.1", 7000).run();
    }


}
