package com.cas.wykt.java高级开发.lock.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:04 2020-07-10
 * @version: V1.0
 * @review: 可重入锁
 */
public class ReentrantLock1 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLock1 lockDemo = new ReentrantLock1();
        new Thread(() -> {
            try {
                lockDemo.test(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread thread = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + "渴望拿到锁");
            try {
                lockDemo.test(name);
                System.out.println(name + " 拿到了锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
//        thread.interrupt();
    }

    // 可重入锁
    public void test(String name) throws InterruptedException {
        try {
            lock.lockInterruptibly(); // 响应中断
            System.out.println(name + " 拿到了锁, 持有锁: " + lock.getHoldCount());
            lock.lockInterruptibly();
            System.out.println(name + " 拿到了锁, 持有锁: " + lock.getHoldCount());
        } finally {
            lock.unlock();
            lock.unlock();
            System.out.println(name + " 释放了, 持有锁: " + lock.getHoldCount());
        }
    }


}
