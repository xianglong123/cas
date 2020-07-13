package com.cas.wykt.java高级开发.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 16:16 2020-07-10
 * @version: V1.0
 * @review: 自写锁
 */
public class XlLock implements Lock {

    AQSdemo aqSdemo = new AQSdemo() {
        @Override
        public boolean tryAcquire() {
            return owner.compareAndSet(null, Thread.currentThread());
        }

        @Override
        public boolean tryRelease() {
            return owner.compareAndSet(Thread.currentThread(),null);
        }
    };

    @Override
    public void lock() {
        aqSdemo.acquire();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return aqSdemo.tryAcquire();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        aqSdemo.release();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
