package com.cas.service.countDownLatchService;

import com.cas.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:30 2020-05-21
 * @version: V1.0
 * @review:
 */
@Slf4j
@Service
public class CountDownLatchServiceClient implements CountDownLatchService{

    private CountDownLatch countDownLatch = new CountDownLatch(0);

    private List<FutureTask<Boolean>> results = new ArrayList<>();

    @Override
    public String subNum(Integer... num) {
        StringBuffer buffer = new StringBuffer();
        Arrays.asList(num).forEach(a -> {
            countDownLatch = new CountDownLatch(a);
            for(int i = 0; i <= a; i ++) {
                int finalI = i;
                ThreadPoolUtil.execute(() -> {
                    System.out.print(finalI + ", ");
                    buffer.append(finalI);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                });
            }
//            ThreadPoolUtil.shutdown();
            buffer.append("\n");
            try {
//                countDownLatch.await();
                boolean await = countDownLatch.await(4, TimeUnit.SECONDS);
                log.info("await-[{}]", await);
            } catch (InterruptedException e) {
                System.out.println("await()超时抛异常");
                e.printStackTrace();
            }
            System.out.println("\n");
        });
        return buffer.toString();
    }


    @Override
    public String process(Integer... num) throws ExecutionException, InterruptedException {
        log.info("process 当前线程-[{}]", Thread.currentThread().getName());
        if(ThreadPoolUtil.isShutdown()) {
            return "o";
        }
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            FutureTask<Boolean> futureTask = new FutureTask<>(() -> {
                try {
                    Thread.sleep( 2000);
                    log.info("任务执行完成, value-[{}], 当前线程-[{}]", finalI, Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    log.info("超时异常");
                }
            }, true);
            ThreadPoolUtil.execute(futureTask);
            results.add(futureTask);
        }

        for (int i = 0; i < 100; i++) {

            FutureTask<Boolean> future = results.get(i);
            // 从future中取出执行结果（若尚未返回结果，则get方法被阻塞，直到结果被返回为止）
            Boolean aBoolean = future.get();
            System.out.println(aBoolean);
        }
        return "o";
    }
}
