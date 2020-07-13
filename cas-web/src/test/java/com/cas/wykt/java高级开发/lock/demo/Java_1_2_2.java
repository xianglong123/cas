package com.cas.wykt.java高级开发.lock.demo;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:55 2020-07-10
 * @version: V1.0
 * @review: 原子操作
 */
public class Java_1_2_2 {


    public static void main(String[] args) {
        incre();
    }

    public static void incre() {
        LongAdder adder = new LongAdder();
        for (int i = 0; i < 10; i ++) {
            new Thread(() -> {
                adder.increment();
            }).start();
        }
        System.out.println(adder.sum());

    }
}
