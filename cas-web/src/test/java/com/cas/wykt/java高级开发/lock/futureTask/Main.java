package com.cas.wykt.java高级开发.lock.futureTask;

import java.util.concurrent.ExecutionException;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:12 2020-07-12
 * @version: V1.0
 * @review:
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        XlFutureTask<String> futureTask = new XlFutureTask<String>(() -> "结果");
        new Thread(futureTask).start();
        String result = futureTask.get();
        System.out.println(result);
    }
}
