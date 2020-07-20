package com.cas.wykt.java高级开发.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:43 2020-07-13
 * @version: V1.0
 * @review: 信号量
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        int N = 9;
        for (int i = 0; i < N; i ++) {
            String vipNo = "vip-00" + i;
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 申请资源，不能申请会阻塞线程
                    service(vipNo);
                    semaphore.release(); // 释放资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }


    public static void service(String vipNo) {
        System.out.println(vipNo + "开始消费");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(vipNo + "已经走了");
    }


}
