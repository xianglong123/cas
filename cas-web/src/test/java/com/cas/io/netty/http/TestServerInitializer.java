package com.cas.io.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:22 2020-11-18
 * @version: V1.0
 * @review:
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器

        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个netty 提供的httpServerCode codec => [coder - decoder]
        // HttpServerCodec 说明
        // 1. HttpServerCodec 是netty 提供的处理http的 端 编-解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());

        // 2. 增加一个处理器
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());

        System.out.println("OK ～～～～");
    }
}
