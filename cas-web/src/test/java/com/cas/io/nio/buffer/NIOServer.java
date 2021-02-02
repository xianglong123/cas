package com.cas.io.nio.buffer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 08:45 2020-11-01
 * @version: V1.0
 * @review: NIO demo-1 [服务器端]
 */
public class NIOServer {

    public static void main(String[] args) throws Exception{

        // 创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到一个Selector对象
        Selector selector = Selector.open();

        // 绑定一个端口6666， 在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把 serverSocketChannel 注册到 selector 关心 事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true) {

            // 这里我们等待1秒，如果没有事件发生，返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            // 如果返回的 > 0, 就获取到相关的 selectionKey集合
            // 1.如果返回的 > 0, 表示已经获取关注的事件
            // 2.selector.selectedKeys() 返回关注事件的集合
            // 通过 selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历 Set<SelectionKey> ， 使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                // 获取到selectionKey
                SelectionKey key = keyIterator.next();
                // 根据key， 对应的通道发生的事件做相应的处理
                if (key.isAcceptable()) { //如果是OP_ACCEPT，有新的客户端连接
                    // 该客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketChannel 设置为非阻塞的
                    socketChannel.configureBlocking(false);

                    System.out.println("客户端连接成功 socketChannel=" + socketChannel.hashCode());
                    // 将socketChannel 注册到selector， 关注事件为OP_READ, 同时给socketChannel
                    // 关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) { // 发生 OP_READ
                    // 通过key 反向获取到对应channel
                    SocketChannel channel = (SocketChannel)key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);

                    System.out.println("form 客户端 " + new String(buffer.array()));
                }

                System.out.println("key 已删除");
                // 手动从集合中移除当前的selectionKey， 防止重复操作
                keyIterator.remove();
            }
        }
    }
}
