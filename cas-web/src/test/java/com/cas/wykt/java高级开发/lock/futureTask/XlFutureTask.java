package com.cas.wykt.java高级开发.lock.futureTask;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:12 2020-07-12
 * @version: V1.0
 * @review:
 */
public class XlFutureTask<T> implements Runnable{

    Callable<T> callable;
    T result;
    String state = "NEW";
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    XlFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            state = "END";
        }

        Iterator<Thread> iterator = waiters.iterator();
        while (iterator.hasNext()) {
            Thread next = iterator.next();
            LockSupport.unpark(next);
        }
    }

    public T get() {
        if ("END".equals(state)) {
            return result;
        }
        waiters.offer(Thread.currentThread());
        LockSupport.park();
        return result;
    }

}
