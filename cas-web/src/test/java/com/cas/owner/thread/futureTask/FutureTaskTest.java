package com.cas.owner.thread.futureTask;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:10 2020-05-25
 * @version: V1.0
 * @review: FutureTask 取消任务
 */
public class FutureTaskTest {

    public static void main(String[] args) throws Exception{
        FutureTask<String> task = new FutureTask<>(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("任务被执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "gogo");
        new Thread(task).start();
        boolean done = task.isDone();
        long startTime = System.currentTimeMillis();
        String s = null;
        try {
            s = task.get(1, TimeUnit.SECONDS);// 这个方法会阻塞，直到任务完成时返回
        } catch (TimeoutException e) {
            task.cancel(true);
        }
        boolean done1 = task.isDone();
        long endTime = System.currentTimeMillis();
        System.out.println("任务返回结果 = "+done+"   "+done1+"    time = "+(endTime-startTime)+"   "+s);
    }

}
