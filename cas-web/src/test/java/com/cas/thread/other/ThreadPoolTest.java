package com.cas.thread.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:05 2020-03-06
 * @version: V1.0
 * @review: 线程池简单例子
 */
@Slf4j
public class ThreadPoolTest {

    @Autowired
    private static DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private static TransactionDefinition transactionDefinition;

    public static void main(String[] args) throws InterruptedException {
//        threadPoolFixExecutor();
//        threadPoolScheduleExecutor();
        threadPoolScheduleWithExecutor();
    }


    private static void threadPoolScheduleExecutor() {

//        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
//
//        dataSourceTransactionManager.commit(transaction);
//
//        dataSourceTransactionManager.rollback(transaction);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行：时间-" + System.currentTimeMillis());
        }, 2000, 1000, TimeUnit.MILLISECONDS);

    }

    private static void threadPoolScheduleWithExecutor() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行：时间-" + System.currentTimeMillis());
        }, 2000, 1000, TimeUnit.MILLISECONDS);

    }

    private static void threadPoolFixExecutor() throws InterruptedException {
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
