package com.cas.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:54 2020-10-31
 * @version: V1.0
 * @review: 测试1、读取的类型要按顺序读取，2、只读buffer不能写数据
 */
public class ByteBufferTest {

    public static void main(String[] args) {
//        writeAndReadBySort();
        readOnlyBuffer();

    }

    /**
     * 如果申明成只读buffer，向里面写数据会报错： java.nio.ReadOnlyBufferException
     */
    private static void readOnlyBuffer() {
        ByteBuffer allocate = ByteBuffer.allocate(20);
        for (int i = 0; i < allocate.limit(); i++) {
            allocate.put((byte) i);
        }

        allocate.flip();
        final ByteBuffer readOnlyBuffer = allocate.asReadOnlyBuffer();

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte) 2);
    }

    /**
     * 读取类型顺序，不然有可能会报错，记住是有可能
     */
    private static void writeAndReadBySort() {
        ByteBuffer allocate = ByteBuffer.allocate(64);

        allocate.putInt(20);
        allocate.putLong(20L);
        allocate.putChar('2');

        allocate.flip();

        System.out.println(allocate.getInt());
        System.out.println(allocate.getLong());
        System.out.println(allocate.getChar());
    }


}
