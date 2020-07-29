package com.cas.owner.socker.nio;

import java.io.IOException;
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
 * @date: 10:17 2020-07-20
 * @version: V1.0
 * @review: NIO + Selector
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        // 1, 创建网络服务端 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // 2, 构建一个Selector选择器，并且将channel注册上去
        Selector selector = Selector.open();
        // 将serverSocketChannel注册到selector
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        // 3, 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        System.out.println("启动成功");

        while (true) {
            // 不再轮询通道，改用下面轮询事件的方式，select方法有阻塞效果，直到有事件通知才会有返回
            selector.select();
            // 获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历查询结果
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                // 被封装的查询结果
                SelectionKey key = iterator.next();
                iterator.remove();

                // 关注 Read 和 Accept两个事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.attachment();
                    // 将拿到的客户端连接通道，注册到selector 上面
                    SocketChannel socketChannel = server.accept();
                    // 设置非阻塞
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, socketChannel);
                    System.out.println("收到新连接 ： " + socketChannel.getRemoteAddress());
                }

                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                            // 长连接情况下，需要手动判读数据有没有读取结束（此处做一个简单的判断，超过0字节就任务请求结束了）
                            if (requestBuffer.position() > 0) break;
                        }
                        // 如果没数据了，则不继续后面的处理
                        if (requestBuffer.position() == 0) continue;
                        requestBuffer.flip();
                        byte[] content = new byte[requestBuffer.limit()];
                        requestBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据来自： " + socketChannel.getRemoteAddress());

                        // 响应结果 200
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()) {
                            socketChannel.write(buffer);
                        }
                    } catch (IOException e) {
                        key.cancel(); // 取消事件订阅
                    }
                }
            }
            selector.selectNow();
        }
    }


}
