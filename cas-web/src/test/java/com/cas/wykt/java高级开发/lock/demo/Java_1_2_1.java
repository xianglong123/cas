package com.cas.wykt.java高级开发.lock.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 14:19 2020-07-09
 * @version: V1.0
 * @review: 关闭jit优化 -D java.compiler=NONE
 */
public class Java_1_2_1 {

    // 关闭jit优化和volatile都能解决问题
    private volatile boolean flag = true;
    private static AtomicInteger i ;


    public static void main(String[] args) throws InterruptedException {
        i.incrementAndGet();
        Java_1_2_1 java121 = new Java_1_2_1();
        new Thread(() -> {
            int i = 0;
            while (java121.flag) {
                i ++;
            }
            System.out.println(i);
        }).start();

        TimeUnit.SECONDS.sleep(2);
        java121.flag = false;
        Thread.sleep(3000);
        System.out.println("被设置为false了");
    }


}
