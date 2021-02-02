package com.cas.io.nio.buffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 08:17 2020-10-31
 * @version: V1.0
 * @review: 测试通过buffer + channel 向文件中写数据
 */
public class FileChannel01Test {
    ///Users/xianglong/IdeaProjects/cas
    public static void main(String[] args) throws IOException {
        String hello = "hello xl";

        // 先说明输出到那个文件内
        final FileOutputStream fileOutputStream = new FileOutputStream("/Users/xianglong/IdeaProjects/cas/hello.txt");

        // 将通道拿出来，用到的类是FileChannelImpl
        FileChannel channel = fileOutputStream.getChannel();

        // 缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将内容读到缓冲区
        byteBuffer.put(hello.getBytes());

        // 读写反转
        byteBuffer.flip();

        // 通道读取缓冲区内容
        channel.write(byteBuffer);

        // 关闭流
        fileOutputStream.close();

    }

}
