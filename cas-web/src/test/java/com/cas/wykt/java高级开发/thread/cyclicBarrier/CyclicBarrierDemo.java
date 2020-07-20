package com.cas.wykt.java高级开发.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:27 2020-07-13
 * @version: V1.0
 * @review:
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> sqls = new LinkedBlockingQueue<>();

        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("线程已经达到4个，开始批量插入：" + Thread.currentThread());
            for (int i = 0; i < 4; i ++) {
                System.out.println(sqls.poll());
            }
        });
//        CyclicBarrier barrier = new CyclicBarrier(4);

        for (int i = 0; i < 6; i ++) {
            new Thread(() -> {
                try {
                    sqls.add("data - " + Thread.currentThread());
                    Thread.sleep(1000L);
                    barrier.await();
                    System.out.println("插入完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

}
