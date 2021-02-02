package com.cas.io.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:10 2020-11-25
 * @version: V1.0
 * @review:
 */
public class GroupChatClienthandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
