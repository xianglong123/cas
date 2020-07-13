package com.cas.wykt.java高级开发.lock.readWriteLock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:03 2020-07-12
 * @version: V1.0
 * @review: 读写锁实现
 */
public class AQSdemo {

    volatile AtomicInteger state = new AtomicInteger(0);

    protected AtomicReference<Thread> owner = new AtomicReference<>();
    // 锁池等待
    public volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();





}
