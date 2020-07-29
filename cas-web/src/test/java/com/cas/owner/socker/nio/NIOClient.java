package com.cas.owner.socker.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:46 2020-07-20
 * @version: V1.0
 * @review:
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        while (!socketChannel.finishConnect()) {
            // 没连接上，则一直等待
            Thread.yield();
        }
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入： ");
            // 发送内容
            String msg = scanner.nextLine();
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            socketChannel.write(buffer);
        }
    }

}
