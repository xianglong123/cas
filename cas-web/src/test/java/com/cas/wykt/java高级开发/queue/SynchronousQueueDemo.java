package com.cas.wykt.java高级开发.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 09:42 2020-07-13
 * @version: V1.0
 * @review: 异步队列
 * 这个队列的put方法回阻塞线程，只有当有人调用take才会让阻塞的线程恢复正常
 */
public class SynchronousQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.put("xl");
    }

}
