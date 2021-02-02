package com.cas.io.nio.buffer;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:10 2020-10-31
 * @version: V1.0
 * @review: MappedByteBuffer 在内存中修改，不需要经过系统拷贝，系统级别的类，性能高
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        final RandomAccessFile rw = new RandomAccessFile("/Users/xianglong/IdeaProjects/cas/hello.txt", "rw");

        final FileChannel channel = rw.getChannel();

        // 参数1、支持读写模式
        // 参数2、0 可以直接修改的位置
        // 参数3、映射到内存可以修改的大小（不是索引），比如5就只支持索引到4
        final MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '1');
        mappedByteBuffer.put(4, (byte) '2');

        rw.close();
        System.out.println("修改成功");

    }


}
