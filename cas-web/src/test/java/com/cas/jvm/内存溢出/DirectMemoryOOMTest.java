package com.cas.jvm.内存溢出;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:05 2020-03-21
 * @version: V1.0
 * @review: VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M 测试直接内存溢出情况
 * 结论：暂时不会出现本地方法栈溢出的情况
 */
public class DirectMemoryOOMTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
            System.out.println("----");
        }
    }
}
