package com.cas.concurrent.串行比并行快吗;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 08:42 2020-03-31
 * @version: V1.0
 * @review:
 */
@Slf4j
public class ConcurrencyTest {

    private static final long count = 1000000001;//分割线：10亿

    public static void main(String[] args) throws InterruptedException{
        log.info("start");
        concurrency();
        serial();
        log.info("end");
    }


    private static void concurrency() throws InterruptedException{
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();

        int b = 0;
        for (long i = 0; i < count; i ++) {
            b --;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency : " + time + "ms, b=" +b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i ++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i <= count; i ++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency : " + time + "ms, b=" + b + ", a=" +a);
    }


}
