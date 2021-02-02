package com.cas.io.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:38 2020-10-29
 * @version: V1.0
 * @review: 测试intbuffer缓冲区
 */
public class IntBufferTest {

    public static void main(String[] args) {
        // 初始化大小
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i< intBuffer.capacity(); i ++) {
            intBuffer.put(i * 2);
        }

        // 下面要读取就先执行这个方法，会对对象的某些标志做改变
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }


    }


}
