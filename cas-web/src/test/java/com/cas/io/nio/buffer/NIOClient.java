package com.cas.io.nio.buffer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:17 2020-11-01
 * @version: V1.0
 * @review: NIO demo-1 [客户端]
 */
public class NIOClient {

    public static void main(String[] args) throws Exception{

        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        // 设置非阻塞
//        socketChannel.configureBlocking(false);

        // 提供服务器端的IP 和 端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {

            while (!socketChannel.finishConnect()) {
                System.out.println("客户端未连接，可以做其他事");
            }
        }

        // 如果连接成功，就发送数据
        String str = "hello xl";

        // wrap方法可以根据内容大小设置buffer大小
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());

        // 发送数据，将buffer 数据写入channel
        socketChannel.write(buffer);

        // 停住
        System.in.read();


    }


}
