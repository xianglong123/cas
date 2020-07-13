package com.cas.wykt.java高级开发.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:47 2020-07-10
 * @version: V1.0
 * @review: 抽象队列同步器
 */
public class AQSdemo {
    AtomicReference<Thread> owner = new AtomicReference<>();
    // 锁池等待
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    public void acquire() {
        while (!tryAcquire()) {
            // 保存等待的线程
            waiters.offer(Thread.currentThread());
            // 进入等待，等待其他线程的释放
            LockSupport.park(); // 不再继续执行，等待unpark
            waiters.remove(Thread.currentThread());
        }
    }

    public boolean tryAcquire() {
        throw new UnsupportedOperationException();
    }

    public void release() {
        if (tryRelease()) {
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()) {
                Thread thread = iterator.next();
                LockSupport.unpark(thread);
            }
        }
    }

    public boolean tryRelease() {
        throw new UnsupportedOperationException();
    }


}
