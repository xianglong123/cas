package com.cas.pool.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:05 2020-03-06
 * @version: V1.0
 * @review: 线程池简单例子
 */
@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        // 初始化线程池
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 创建任务
        Collection<Callable<String>> list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            list.add(() -> {
                System.out.println("线程已执行" + Thread.currentThread().getName());
                return "ok";
            });
        }
        // 执行线程
        pool.invokeAll(list);
        // 关闭线程池
        pool.shutdown();
    }

}
