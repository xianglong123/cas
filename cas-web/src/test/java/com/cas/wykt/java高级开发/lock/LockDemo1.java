package com.cas.wykt.java高级开发.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:30 2020-07-10
 * @version: V1.0
 * @review:
 */
public class LockDemo1 {

    private static Lock lock = new XlLock();

    private volatile int index = 0;

    public static void main(String[] args) throws InterruptedException {
        LockDemo1 lockDemo1 = new LockDemo1();
        for (int i = 0; i <= 20; i ++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j ++) {
                    lockDemo1.inrc();
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(lockDemo1.index);
    }

    public void inrc() {
        lock.lock();
        try {
            index ++;
        } finally {
            lock.unlock();
        }
    }

}
