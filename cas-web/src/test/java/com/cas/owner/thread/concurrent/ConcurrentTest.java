package com.cas.owner.thread.concurrent;

import com.cas.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 2:28 PM 12/17/20
 * @version: V1.0
 * @review: 高并发接口解决方案
 */
public class ConcurrentTest extends BaseTest {

    private static int MAX_THREAD = 1000;
    private static CountDownLatch cdl = new CountDownLatch(MAX_THREAD);
    // 单机用LinkedBlockingQueue试试没问题，推荐用redis做任务队列
    private static LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    @Autowired
    private RedisTemplate redisTemplate;


    static class Request {
        String serialNo;
        String value;
        CompletableFuture<String> future;
    }

    @Test
    public void test() throws InterruptedException{
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name.toString());
//        for (int i = 0; i < MAX_THREAD; i ++) {
//            int finalI = i;
//            Thread t = new Thread(() -> {
//                try {
//                    cdl.countDown();
//                    cdl.await();
//                    getResult(finalI);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            });
//            t.start();
//        }
//
//        // 主线程休眠两秒获取结果
//        Thread.sleep(2000);
    }


    private static String getResult(int i) throws ExecutionException, InterruptedException {

        String serialNo = UUID.randomUUID().toString();
        // 监听的类
        CompletableFuture<String> future = new CompletableFuture<>();

        Request request = new Request();
        request.serialNo = serialNo;
        request.future = future;
        request.value = String.valueOf(i);
        queue.add(request);
        return future.get(); // 阻塞
    }

    /**
     * 任务分发：降低某一时刻并发量
     */
    @PostConstruct
    private static void init() {
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
        s.scheduleAtFixedRate(() -> {
            int size = queue.size();
            if (size == 0) {
                return;
            }

            List<Request> requests = new ArrayList<>();

            for (int i = 0; i < size; i ++) {
                Request poll = queue.poll();
                requests.add(poll);
            }

            System.out.println("批量处理的数据量" + size);

            for (Request request : requests) {
                request.future.complete("1");
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }



}
