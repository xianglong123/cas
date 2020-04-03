package com.cas.concurrent.死锁;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 22:20 2020-04-02
 * @version: V1.0
 * @review: -XX:+PrintGCDetails
 */
public class DeadLockDemo {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("l");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }
        });
        t1.start();
        t2.start();
    }

}
