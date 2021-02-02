package com.cas.io.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:58 2020-11-25
 * @version: V1.0
 * @review: 这里 TextWebSocketFrame 类型，表示一个文本帧（frame）
 */
public class MyTestWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("服务器收到消息 " + msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + " " + msg.text()));
        pushLog(ctx);
    }

    // 类似与linux 的tail 命令，读取增量数据（因为有字段记录）
    private void pushLog(ChannelHandlerContext ctx) {
        String cmd = "tail -100 /Users/xianglong/Desktop/test.log";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            //需要另外启动线程进行读取，防止输入流阻塞当前线程
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), "UTF-8"));
                String lineOne = null;
                while ((lineOne = br.readLine()) != null) {
                    System.out.println(lineOne);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(lineOne + "</br>"));
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
    }


    // 当web客户端连接后，触发方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // id 表示唯一的值， LongText 是唯一的 shortText 不是唯一
        System.out.println("handlerAdded 被调用" + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved 被调用" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生" + cause.getMessage());
        ctx.close(); // 关闭通道/连接
    }
}
