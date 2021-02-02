package com.cas.io.nio.buffer.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:27 2020-11-10
 * @version: V1.0
 * @review:
 */
public class GroupChatServer {

    // 定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    // 构造器
    public GroupChatServer() {

        try {
            // 得到选择器
            selector = Selector.open();
            // ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞模式
            listenChannel.configureBlocking(false);
            // 将sockerChannel注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听
    public void listen() {

        try {

            // 循环处理
            while (true) {

                int count = selector.select();
                if (count > 0) { // 有事件处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while(iterator.hasNext()) {
                        // 取出selectorKey
                        SelectionKey key = iterator.next();

                        // 监听到accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            // 将sc 注册到selector
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " 上线");
                        }

                        if (key.isReadable()) { // 通道发送read事件，即通道是可读状态
                            // 处理读 （专门写方法）
                            readData(key);
                        }
                        // 删除key, 防止重复
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待....");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读客户端消息
    private void readData(SelectionKey key) {

        // 读取关联的channel
        SocketChannel channel = null;

        try {
            // 得到关联的channel
            channel = (SocketChannel) key.channel();

            // 创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = channel.read(buffer);
            //根据count的值做处理
            if (count > 0) {
                // 把缓存区的数据转成字符串
                String msg = new String(buffer.array());

                // 输出该消息
                System.out.println("form 客户端: " + msg);

                // 向其他的客户端转发消息（排除自己），专门写方法处理
                sendInfoToOtherClients(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了...");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 转发消息给其他客户（通道）
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException{

        System.out.println("服务器转发消息中...");

        // 遍历所有注册到selector上的socketChannel, 并排除 self
        for (SelectionKey key : selector.keys()) {

            // 通过key取出对应的socketChannel
            SelectableChannel targetChannel = key.channel();

            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {

                // 转型
                SocketChannel dest = (SocketChannel) targetChannel;
                // 将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 将buffer的数据写入通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }


}
