package com.cas.io.nio.buffer;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:32 2020-10-31
 * @version: V1.0
 * @review: scattering: 将数据写入到buffer时，可以采用buffer数组，依次写入 【分散】
 * gathering: 从buffer读取数据时，可以采用buffer数组，依次读 【聚合】
 */
public class ScatteringAndGatheringTest {


    public static void main(String[] args) throws Exception {

        ServerSocketChannel open = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        // 绑定端口到socket, 并启动
        open.socket().bind(inetSocketAddress);

        // 创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        // 等待客户端连接(telnet)
        SocketChannel socketChannel = open.accept();
        int messageLenth = 8;

        // 循环读取
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLenth) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map(buffer -> "postion=" +
                        buffer.position() + ", limit=" + buffer.limit()).forEach(System.out::println);
            }

            // 将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            // 将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLenth) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead=" + byteRead + ", byteWrite=" + byteWrite);

        }


    }


}
