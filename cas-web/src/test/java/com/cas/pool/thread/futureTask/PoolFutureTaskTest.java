package com.cas.pool.thread.futureTask;

import com.cas.BaseTest;
import com.cas.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 10:55 2020-05-25
 * @version: V1.0
 * @review: 线程池 + futureTask + CountDownLatch实现中断超时任务
 */
@Slf4j
public class PoolFutureTaskTest {

    @Test
    public void test() {
        Integer[] integers = {1, 2, 3, 4, 5};
        for(int i = 0; i < integers.length; i ++) {
            int finalI = i;
            FutureTask<Boolean> futureTask = new FutureTask<>(() -> {
                log.info("任务执行完成, value-[{}]", finalI);
                try {
                    Thread.sleep(finalI * 1000);
                } catch (InterruptedException e) {
                    log.info("超时异常");
                    e.printStackTrace();
                }
            }, true);
            ThreadPoolUtil.execute(futureTask);
            Boolean isDone = false;
            try {
                isDone = futureTask.get(3, TimeUnit.SECONDS);
                log.info("3秒超时获得结果-【{}】", isDone);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            if(!isDone) {
                // 取消任务
                futureTask.cancel(true);
            }


        }

    }



}
