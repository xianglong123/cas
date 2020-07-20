package com.cas.wykt.java高级开发.thread.countDownLatch;

import com.cas.owner.utils.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:44 2020-07-13
 * @version: V1.0
 * @review: 并发执行：意思就是说latch之后的线程都就绪之后才会执行
 */
public class CountDownLachDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList<String> list = new ArrayList<>();
        ThreadPoolUtil.execute(() -> {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("1");
            System.out.println("1");
            countDownLatch.countDown();
        });
        ThreadPoolUtil.execute(() -> {
            list.add("2");
//            System.out.println("2");
            countDownLatch.countDown();
        });

        countDownLatch.await(0, TimeUnit.SECONDS);
        // await带时间的会在时间后直接返回结果，其他的结果不再关心，但是那个线程还是会执行，不会中断
        list.forEach(System.out::println);
        System.out.println("执行完毕");

    }
}
