package com.cas.owner.http;

import com.cas.owner.utils.httpUtils.HttpClientUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 18:08 2020-05-19
 * @version: V1.0
 * @review:
 */
public class HttpTest {

    public static void main(String[] args) throws InterruptedException {
        String jsonData = "{\"typeCd\":\"0016\",\"keyType\":\"0\",\"keys\":\"15811317735\",\"content\":\"我2们都是短信制造者\"}";
        String url = "http://127.0.0.1:8086/smsService/sms/batchSendSmsWithJson";
        // 初始化线程池
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(30);
        // 创建任务
        Collection<Callable<String>> list = new ArrayList<>();
        for (int i = 0; i <= 3000; i++) {
            list.add(() -> {
                String json = HttpClientUtil.postJSON(url, jsonData);
                System.out.println( "    " + json);
                return "ok";
            });
        }
        // 执行线程
        pool.invokeAll(list);
        // 关闭线程池
        pool.shutdown();
    }

}
