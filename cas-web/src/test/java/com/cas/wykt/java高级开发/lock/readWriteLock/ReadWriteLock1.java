package com.cas.wykt.java高级开发.lock.readWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:40 2020-07-10
 * @version: V1.0
 * @review: 读写锁
 */
public class ReadWriteLock1 {
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReadWriteLock1 readWriteLock = new ReadWriteLock1();
        new Thread(() -> readWriteLock.read2(Thread.currentThread())).start();
//        new Thread(() -> readWriteLock.write2(Thread.currentThread())).start();
//        new Thread(() -> readWriteLock.write2(Thread.currentThread())).start();
        new Thread(() -> readWriteLock.read2(Thread.currentThread())).start();

        new Thread(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readWriteLock.write2(Thread.currentThread());
        }).start();
        new Thread(() -> readWriteLock.write2(Thread.currentThread())).start();
    }

    /**
     * 排他线程安全，性能低
     * @param thread
     */
    public synchronized void read(Thread thread) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + " 正在读");
        }
        System.out.println(thread.getName() + " 读已完毕");
    }

    /**
     * 排他线程安全，性能低
     * @param thread
     */
    public synchronized void write(Thread thread) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + " 正在写");
        }
        System.out.println(thread.getName() + " 写已完毕");
    }

    /**
     * 读写锁，多读单写
     * @param thread
     */
    public void read2(Thread thread) {
        readWriteLock.readLock().lock();
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + " 正在读");
        }
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        readWriteLock.readLock().unlock();
        System.out.println(thread.getName() + " 读已完毕,锁释放");
    }

    public void write2(Thread thread) {
        readWriteLock.writeLock().lock();
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + " 正在写");
        }
        readWriteLock.writeLock().unlock();
        System.out.println(thread.getName() + " 写已完毕,锁释放");
    }

}
