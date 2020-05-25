package com.cas.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 16:43 2020-05-11
 * @version: V1.0
 * @review: 线程池复用
 */
@Slf4j
@Component
public class ThreadPoolUtil {

    private static  ThreadPoolExecutor poolExecutor = null;

    {
        // 核心线程池5，最长闲置时间30秒，拒绝策略：忽略
        poolExecutor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(12), new ThreadPoolExecutor.DiscardPolicy());
    }

    /**
     * 调用线程池的方法添加任务
     * @param commond
     */
    public static void execute(Runnable commond) {
        if (poolExecutor == null) {
            poolExecutor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(12), new ThreadPoolExecutor.DiscardPolicy());
        }
        poolExecutor.execute(commond);
    }

    /**
     * 释放线程池资源
     */
    public static void shutdown() {
        if(poolExecutor != null) {
            log.info("正在执行任务总量-[{}]", poolExecutor.getActiveCount());
            poolExecutor.shutdown();
        }
    }

    public static boolean isShutdown() {
        if(poolExecutor == null) {
            return true;
        }
        return poolExecutor.isShutdown();
    }

}
