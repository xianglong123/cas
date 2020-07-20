package com.cas.owner.socker.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:42 2020-07-19
 * @version: V1.0
 * @review:
 */
public class BufferDemo {

    public static void main(String[] args) {
        // 构建一个byte字节缓冲区。容量是4
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        // 默认是写入模式，查看是那个重要的指标
        System.out.println(String.format("初始化：capacity容量： %s, position位置：%s, " +
                "limit限制：%s", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        // 写入2字节的数据
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);

        // 查看数据
        System.out.println(String.format("写入3字节后，capacity容量： %s, position位置：%s, " +
                "limit限制：%s", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));

        System.out.println("######开始读取");
        byteBuffer.flip();

        byte a = byteBuffer.get();
        System.out.println(a);

        byte b = byteBuffer.get();
        System.out.println(b);

        System.out.println(String.format("读取数据后，capacity容量： %s, position位置：%s, " +
                "limit限制：%s", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));

        // 清楚已阅读的数据
        byteBuffer.compact();
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 6);

        System.out.println(String.format("最终结果，capacity容量： %s, position位置：%s, " +
                "limit限制：%s", byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));



    }

}
