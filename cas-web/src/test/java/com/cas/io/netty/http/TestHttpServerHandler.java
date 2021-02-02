package com.cas.io.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:22 2020-11-18
 * @version: V1.0
 * @review: HttpObject 客户端和服务端相互通讯的数据被封装成 HttpObject
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    // 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println("channel=" + ctx.channel() + " pipeline=" + ctx.pipeline());
        System.out.println("当前的handler=" + ctx.handler());

        // 判断 msg 是不是 HttpRequest 请求
        if (msg instanceof HttpRequest) {

            System.out.println("ctx 类型=" + ctx.getClass());

            System.out.println("pipeline hashcode" + ctx.pipeline().hashCode()
            + " TestHttpServerHandler Hash=" + this.hashCode());

            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端求地址" + ctx.channel().remoteAddress());

            // 获取到
            HttpRequest httpRequest = (HttpRequest) msg;
            // 获取uri
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println(" 请求了， 不做响应");
                return;
            }


            // 回复信息给浏览器 【http协议】
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

            // 构建一个http的响应，
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好 response 返回
            ctx.writeAndFlush(response);
        }
    }

}
