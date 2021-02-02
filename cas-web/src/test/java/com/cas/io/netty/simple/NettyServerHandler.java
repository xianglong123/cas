package com.cas.io.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:34 2020-11-13
 * @version: V1.0
 * @review:
 * 说明
 * 1。 我们自定义一个 Handler 需要继续netty 绑定好的某个HandlerAdapter(规范)
 * 2。 这时我们自定义一个Handler， 才能称为一个Handler
 *
 *
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据实际（这里我们可以读取客户端发送的消息）
    /*
    * 1. ChannelHandlerContext ctx:上下文对象， 含有 管道pipline, 通道channel, 地址
    * 2. Object msg： 就是客户端发送的数据，默认Object
    * */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx =" + ctx);

        // 将msg转成一个 ByteBuf
        // ByteBuf 是netty提供的 不是NIO 的 ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是： " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址： " + ctx.channel().remoteAddress());
    }


    // 数据读取完毕


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        // writeAndFlush 是 write + flush
        // 将数据写入到缓存， 并刷新
        // 一般讲， 我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello，客户端～", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
