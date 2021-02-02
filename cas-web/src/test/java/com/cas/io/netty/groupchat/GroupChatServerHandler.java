package com.cas.io.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:42 2020-11-25
 * @version: V1.0
 * @review:
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个channel 组， 管理所有的channel
    // GlobalEventExecutor.INSTANCE 全局事件执行器，是一个单利
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // handlerAdded 表示连接建立，一旦连接，第一个被执行
    // 将当前channel 加入到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户加入聊天的信息推送给其他在线的客户端
        channelGroup.writeAndFlush("[客户端]" + sdf + " " + channel.remoteAddress() + "加入聊天\n");
        channelGroup.add(channel);
    }

    // 表示channel 处于活动状态，提示 XX上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了");
    }

    // 表示channel 处于不活动状态，提示 XX 离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了");
    }

    // 断开连接，将XX客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + sdf + " " + channel.remoteAddress() + " 离开了\n");
        System.out.println("当前channelGroup size " + channelGroup.size());
    }

    // 真正处理转发
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        // 获取当当前channel
        Channel channel = ctx.channel();
        // 遍历 channelGroup， 根据不同的情况， 回送不同的消息

        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush("[客户]" + sdf + " " + channel.remoteAddress() + " 发送了消息" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己]发送了消息" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常就关闭通道
        ctx.close();
    }
}
