package com.cas.owner.thread.other;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:30 2020-07-08
 * @version: V1.0
 * @review:
 */
public class Lock {

    private Object baozidian = null;

    /** 正常的park/unpark */
    public void parkUnparkTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                LockSupport.park();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("3、通知消费者");
    }

    /** 死锁的park/unpark */
    public void parkUnparkDeadLockTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    LockSupport.park();
                }
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3、通知消费者");
    }

    public static void main(String[] args) throws Exception {
//        new Lock().parkUnparkTest();
        new Lock().parkUnparkDeadLockTest();

    }

}
