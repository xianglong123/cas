package com.cas.wykt.java高级开发.lock.demo;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:42 2020-07-10
 * @version: V1.0
 * @review: 可重入锁 synchronized
 */
public class Java_1_2_3 {


    public static void main(String[] args) {
        new Java_1_2_3().test1(null);
    }

    /**
     * 可重复锁，可重复申请资源
     * @param arg
     */
    private synchronized void test1 (Object arg) {
        System.out.println("开始执行 " + arg);
        if (arg == null) {
            test1(new Object());
        }
        System.out.println("执行结束 " + arg);
    }


}
