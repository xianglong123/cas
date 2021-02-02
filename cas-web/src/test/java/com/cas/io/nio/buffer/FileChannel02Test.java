package com.cas.io.nio.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 08:17 2020-10-31
 * @version: V1.0
 * @review: 测试通过buffer + channel 从文件中读取数据输出到控制台
 */
public class FileChannel02Test {
    ///Users/xianglong/IdeaProjects/cas
    public static void main(String[] args) {
        final File file = new File("/Users/xianglong/IdeaProjects/cas/hello.txt");

        try (final FileInputStream fileInputStream = new FileInputStream(file)) {
            // 申明一个通道
            final FileChannel channel = fileInputStream.getChannel();

            // 申明一个缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

            // 把通道中的数据读取到缓冲区
            channel.read(byteBuffer);

            System.out.println(new String(byteBuffer.array()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
